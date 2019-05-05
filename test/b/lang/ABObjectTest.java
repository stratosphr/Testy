package b.lang;

import b.parser.ASTAnalyzer;
import b.parser.BParser;
import b.parser.SimpleNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by gvoiron on 05/05/19.
 * Time : 02:09
 */
class ABObjectTest {

    @Test
    void toString_ok() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/arithExpr.mch"));
        SimpleNode expr = assertDoesNotThrow(BParser::parseExpr0);
        Object analyzedExpr = expr.jjtAccept(new ASTAnalyzer(), null);
        assertEquals("((((var1 + ((((((var2 - var3) - 4.0) - var5) + var1) + var1) * var1)) + (3.0 / 2.0)) + (var4 % 3.0)) + 1.0)", analyzedExpr.toString());
    }

    @Test
    void hashCode_ok() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/arithExpr.mch"));
        SimpleNode expr = assertDoesNotThrow(BParser::parseExpr0);
        Object analyzedExpr = expr.jjtAccept(new ASTAnalyzer(), null);
        assertEquals("((((var1 + ((((((var2 - var3) - 4.0) - var5) + var1) + var1) * var1)) + (3.0 / 2.0)) + (var4 % 3.0)) + 1.0)".hashCode(), analyzedExpr.hashCode());
    }

    @Test
    void equals_true() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/arithExpr.mch"));
        SimpleNode expr = assertDoesNotThrow(BParser::parseExpr0);
        Object analyzedExpr = expr.jjtAccept(new ASTAnalyzer(), null);
        assertEquals(analyzedExpr, analyzedExpr);
    }

    @Test
    void equals_false() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/arithExpr.mch"));
        SimpleNode expr = assertDoesNotThrow(BParser::parseExpr0);
        Object analyzedExpr = expr.jjtAccept(new ASTAnalyzer(), null);
        assertNotEquals("((((var1 + ((((((var2 - var3) - 4.0) - var5) + var1) + var1) * var1)) + (3.0 / 2.0)) + (var4 % 3.0)) + 1.0)", analyzedExpr);
    }

}