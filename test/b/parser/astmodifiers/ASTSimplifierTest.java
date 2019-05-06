package b.parser.astmodifiers;

import b.parser.BParser;
import b.parser.SimpleNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Created by gvoiron on 06/05/19.
 * Time : 20:27
 */
class ASTSimplifierTest {

    @Test
    public void simplifyArithExpr_ok() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/arithExpr.mch"));
        SimpleNode expr = assertDoesNotThrow(BParser::parseExpr0);
        expr.jjtAccept(new ASTSimplifier(), null);
    }

}