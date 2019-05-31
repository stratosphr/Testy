package b.lang;

import b.lang.exprs.arith.IntVar;
import b.lang.exprs.arith.RealVar;
import b.parser.ASTMachine;
import b.parser.BParser;
import b.parser.astvisitors.ASTToBTranslator;
import b.parser.astvisitors.ASTTypeChecker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by gvoiron on 29/05/19.
 * Time : 15:42
 */
class AObjectTest {

    @Test
    void hashCode_ok() {
        RealVar realFB = new RealVar("FB");
        RealVar realEa = new RealVar("Ea");
        IntVar intFB = new IntVar("FB");
        IntVar intEa = new IntVar("Ea");
        assertEquals(realFB.hashCode(), realFB.hashCode());
        assertEquals(intFB.hashCode(), intFB.hashCode());
        assertNotEquals(intFB.hashCode(), realFB.hashCode());
        assertNotEquals(intFB.hashCode(), realEa.hashCode());
        assertNotEquals(intEa.hashCode(), realFB.hashCode());
        assertNotEquals(intEa.hashCode(), realEa.hashCode());
    }

    @Test
    void equals_ok() {
        RealVar realFB = new RealVar("FB");
        RealVar realEa = new RealVar("Ea");
        IntVar intFB = new IntVar("FB");
        IntVar intEa = new IntVar("Ea");
        IntVar nullVar = new IntVar("null");
        assertEquals(realFB, realFB);
        assertEquals(intFB, intFB);
        assertEquals(realFB, new RealVar("FB"));
        assertEquals(intFB, new IntVar("FB"));
        assertEquals(realEa, new RealVar("Ea"));
        assertEquals(intEa, new IntVar("Ea"));
        assertNotEquals(realFB, realEa);
        assertNotEquals(realFB, intFB);
        assertNotEquals(realFB, intEa);
        assertNotEquals(intFB, realFB);
        assertNotEquals(intFB, realEa);
        assertNotEquals(intFB, intEa);
        assertNotEquals(null, nullVar);
        assertNotEquals(new RealVar("Ea"), new RealVar("FB"));
        assertNotEquals(new IntVar("Ea"), new IntVar("FB"));
        assertNotEquals(new RealVar("Ea"), new RealVar("Test"));
        assertNotEquals(new IntVar("Ea"), new IntVar("Test"));
    }

    @Test
    void parsedMachine_equalsMachineToString() {
        assertDoesNotThrow(() -> BParser.setInputFile("src/test/resources/elec.mch"));
        ASTMachine machineNode1 = assertDoesNotThrow(() -> (ASTMachine) BParser.parseMachine());
        assertTrue(new ASTTypeChecker().checkTypes(machineNode1).getErrors().isEmpty());
        Machine machine1 = new ASTToBTranslator().translate(machineNode1);
        assertDoesNotThrow(() -> BParser.setInputText(machine1.toString()));
        ASTMachine machineNode2 = assertDoesNotThrow(() -> (ASTMachine) BParser.parseMachine());
        assertTrue(new ASTTypeChecker().checkTypes(machineNode2).getErrors().isEmpty());
        Machine machine2 = new ASTToBTranslator().translate(machineNode2);
        assertEquals(machine1, machine2);
    }

}