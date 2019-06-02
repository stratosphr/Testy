package b.lang;

import b.lang.exprs.FunCall;
import b.lang.exprs.arith.Int;
import b.lang.exprs.arith.IntVar;
import b.lang.exprs.string.StringVar;
import b.parser.ASTMachine;
import b.parser.BParser;
import b.parser.astvisitors.ASTToBTranslator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by gvoiron on 02/06/19.
 * Time : 23:48
 */
class MachineTest {

    @Test
    void getAssignables_ok() {
        assertDoesNotThrow(() -> BParser.setInputFile("src/test/resources/elec.mch"));
        ASTMachine machineNode = assertDoesNotThrow(() -> (ASTMachine) BParser.parseMachine());
        Machine machine = assertDoesNotThrow(() -> new ASTToBTranslator().translate(machineNode));
        assertTrue(machine.getEvents().containsKey("Tic"));
        assertEquals(new LinkedHashSet<>(Arrays.asList(
                new StringVar("h"),
                new IntVar("sw"),
                new FunCall("bat", new Int(1)),
                new FunCall("bat", new Int(2)),
                new FunCall("bat", new Int(3)))
        ), machine.getAssignables());
    }

}