package b.parser.astmodifiers;

import b.parser.BParser;
import b.parser.SimpleNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by gvoiron on 06/05/19.
 * Time : 20:27
 */
class ASTSimplifierTest {

    @Test
    public void simplifySingleVarExpr_ok() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/singleVarExpr.mch"));
        SimpleNode expr = assertDoesNotThrow(BParser::parseExpr0);
        SimpleNode simplifiedNode = new ASTSimplifier().simplify(expr);
        assertEquals("Identifier[var1](3, 9)", simplifiedNode.toString());
    }

    @Test
    public void simplifyArithExpr_ok() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/arithExpr.mch"));
        SimpleNode expr = assertDoesNotThrow(BParser::parseExpr0);
        SimpleNode simplifiedNode = new ASTSimplifier().simplify(expr);
    }

}