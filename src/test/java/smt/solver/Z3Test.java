package smt.solver;

import com.microsoft.z3.Context;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Created by gvoiron on 02/06/19.
 * Time : 16:17
 */
class Z3Test {

    @Test
    void checkSAT() {
        assertDoesNotThrow(() -> new Context().mkInt(4));
    }

}