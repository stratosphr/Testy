package b.parser.astvisitors;

import b.lang.Machine;
import b.parser.ASTMachine;
import b.parser.BParser;
import b.parser.SimpleNode;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by gvoiron on 21/05/19.
 * Time : 03:31
 */
class ASTToBTranslatorTest {

    @Test
    void translate_simpleMachine_ok() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/elec.mch"));
        ASTMachine machineNode = assertDoesNotThrow(() -> (ASTMachine) BParser.parseMachine());
        assertTrue(new ASTTypeChecker().checkTypes(machineNode).isEmpty());
        Machine machine = new ASTToBTranslator().translate(machineNode);
        String expectedFormatting = assertDoesNotThrow(() -> Files.readString(Paths.get("res/elec.mch"), UTF_8));
        assertEquals(expectedFormatting, machine.toString());
    }

    @Test
    void translate_simplifiedSimpleMachine_ok() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/elec.mch"));
        SimpleNode machine = assertDoesNotThrow(BParser::parseMachine);
        ASTMachine simplifiedMachine = assertDoesNotThrow(() -> (ASTMachine) new ASTSimplifier().simplify(machine));
        new ASTToBTranslator().translate(simplifiedMachine);
    }

}