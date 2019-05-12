package b.parser.astvisitors;

import b.lang.Machine;
import b.parser.ASTMachine;
import b.parser.BParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 00:59
 */
class ASTToBBuilderTest {

    @Test
    public void buildMachine_ok() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/machine.mch"));
        ASTMachine astMachine = assertDoesNotThrow(() -> (ASTMachine) BParser.parseMachine());
        ASTMachine simplifiedASTMachine = assertDoesNotThrow(() -> (ASTMachine) new ASTSimplifier().simplify(astMachine));
        Machine machine = assertDoesNotThrow(() -> new ASTToBBuilder().build(simplifiedASTMachine));
        assertEquals(12, machine.getConstDefs().size());
    }

}