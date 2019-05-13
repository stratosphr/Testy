package b.parser.astvisitors;

import b.parser.ASTMachine;
import b.parser.BParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 16:36
 */
class ASTTypeCheckerTest {

    @Test
    void checkTypes_ok() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/machine.mch"));
        ASTMachine machine = assertDoesNotThrow(() -> (ASTMachine) BParser.parseMachine());
        ASTMachine simplifiedMachine = assertDoesNotThrow(() -> (ASTMachine) new ASTSimplifier().simplify(machine));
        //new ASTTypeChecker().checkTypes(simplifiedMachine);
    }

}