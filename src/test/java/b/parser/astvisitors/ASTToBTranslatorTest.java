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
    void translate_elec_ok() {
        assertDoesNotThrow(() -> BParser.setInputFile("src/test/resources/elec.mch"));
        ASTMachine machineNode = assertDoesNotThrow(() -> (ASTMachine) BParser.parseMachine());
        assertTrue(new ASTTypeChecker().checkTypes(machineNode).getErrors().isEmpty());
        Machine machine = new ASTToBTranslator().translate(machineNode);
        String expectedFormatting = assertDoesNotThrow(() -> Files.readString(Paths.get("src/test/resources/elec.mch"), UTF_8));
        assertEquals(expectedFormatting, assertDoesNotThrow(machine::toString));
        assertEquals(1, machine.getEvents().size());
        assertTrue(machine.getEvents().containsKey("Tic"));
    }

    @Test
    void translate_simplifiedElec_ok() {
        assertDoesNotThrow(() -> BParser.setInputFile("src/test/resources/elec.mch"));
        SimpleNode machineNode = assertDoesNotThrow(BParser::parseMachine);
        ASTMachine simplifiedMachine = assertDoesNotThrow(() -> (ASTMachine) new ASTSimplifier().simplify(machineNode));
        assertTrue(new ASTTypeChecker().checkTypes(simplifiedMachine).getErrors().isEmpty());
        Machine machine = new ASTToBTranslator().translate(simplifiedMachine);
        String expectedFormatting = assertDoesNotThrow(() -> Files.readString(Paths.get("src/test/resources/elec.mch"), UTF_8));
        assertEquals(expectedFormatting, assertDoesNotThrow(machine::toString));
    }

    @Test
    void translate_erroneousMachine_throwsTypeCheckError() {
        assertDoesNotThrow(() -> BParser.setInputFile("src/test/resources/machine_typeErrors.mch"));
        SimpleNode machineNode = assertDoesNotThrow(BParser::parseMachine);
        ASTMachine simplifiedMachine = assertDoesNotThrow(() -> (ASTMachine) new ASTSimplifier().simplify(machineNode));
        assertFalse(new ASTTypeChecker().checkTypes(simplifiedMachine).getErrors().isEmpty());
        assertThrows(TypeCheckError.class, () -> new ASTToBTranslator().translate(simplifiedMachine));
    }

}