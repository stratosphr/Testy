package b.lang.substitutions;

import b.lang.Machine;
import b.parser.ASTMachine;
import b.parser.BParser;
import b.parser.astvisitors.ASTToBTranslator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by gvoiron on 02/06/19.
 * Time : 23:33
 */
class ASubstitutionTest {

    @Test
    void getPrd_ok() {
        assertDoesNotThrow(() -> BParser.setInputFile("src/test/resources/elec.mch"));
        ASTMachine machineNode = assertDoesNotThrow(() -> (ASTMachine) BParser.parseMachine());
        Machine machine = assertDoesNotThrow(() -> new ASTToBTranslator().translate(machineNode));
        assertTrue(machine.getEvents().containsKey("Tic"));
    }

}