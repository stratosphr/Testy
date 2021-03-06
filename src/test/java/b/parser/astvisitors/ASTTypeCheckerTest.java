package b.parser.astvisitors;

import b.parser.ASTMachine;
import b.parser.BParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 16:36
 */
class ASTTypeCheckerTest {

    @Test
    void checkTypes_ok() {
        assertDoesNotThrow(() -> BParser.setInputFile("src/test/resources/machine.mch"));
        ASTMachine machine = assertDoesNotThrow(() -> (ASTMachine) BParser.parseMachine());
        assertDoesNotThrow(() -> new ASTTypeChecker().checkTypes(machine));
    }

    @Test
    void checkTypes_simplified_ok() {
        assertDoesNotThrow(() -> BParser.setInputFile("src/test/resources/machine.mch"));
        ASTMachine machine = assertDoesNotThrow(() -> (ASTMachine) BParser.parseMachine());
        ASTMachine simplifiedMachine = assertDoesNotThrow(() -> (ASTMachine) new ASTSimplifier().simplify(machine));
        assertDoesNotThrow(() -> new ASTTypeChecker().checkTypes(simplifiedMachine));
    }

    @Test
    void checkTypes_errors() {
        assertDoesNotThrow(() -> BParser.setInputFile("src/test/resources/machine_typeErrors.mch"));
        ASTMachine machine = assertDoesNotThrow(() -> (ASTMachine) BParser.parseMachine());
        List<String> errors = new ASTTypeChecker().checkTypes(machine).getErrors();
        assertEquals(123, errors.size());
    }

}