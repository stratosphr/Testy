package b.parser.astmodifiers;

import b.parser.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue(simplifiedNode instanceof ASTIdentifier);
        assertEquals("var1", simplifiedNode.jjtGetValue());
        assertEquals(3, simplifiedNode.getSourceCoordinates().getLineStart());
        assertEquals(3, simplifiedNode.getSourceCoordinates().getLineEnd());
        assertEquals(9, simplifiedNode.getSourceCoordinates().getColumnStart());
        assertEquals(12, simplifiedNode.getSourceCoordinates().getColumnEnd());
    }

    @Test
    public void simplifyArithExpr_ok() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/arithExpr.mch"));
        SimpleNode expr = assertDoesNotThrow(BParser::parseExpr0);
        SimpleNode simplifiedNode = new ASTSimplifier().simplify(expr);
        assertEquals(15, simplifiedNode.jjtGetNumChildren());
        assertTrue(simplifiedNode instanceof ASTPlus);
        assertTrue(simplifiedNode.jjtGetChild(0) instanceof ASTInt);
        assertTrue(simplifiedNode.jjtGetChild(1) instanceof ASTTimes);
        assertTrue(simplifiedNode.jjtGetChild(2) instanceof ASTMod);
        assertTrue(simplifiedNode.jjtGetChild(3) instanceof ASTInt);
        assertTrue(simplifiedNode.jjtGetChild(4) instanceof ASTInt);
        assertTrue(simplifiedNode.jjtGetChild(5) instanceof ASTDouble);
        assertTrue(simplifiedNode.jjtGetChild(6) instanceof ASTInt);
        assertTrue(simplifiedNode.jjtGetChild(7) instanceof ASTInt);
        assertTrue(simplifiedNode.jjtGetChild(8) instanceof ASTInt);
        assertTrue(simplifiedNode.jjtGetChild(9) instanceof ASTInt);
        assertTrue(simplifiedNode.jjtGetChild(10) instanceof ASTInt);
        assertTrue(simplifiedNode.jjtGetChild(11) instanceof ASTUMinus);
        assertTrue(simplifiedNode.jjtGetChild(12) instanceof ASTInt);
        assertTrue(simplifiedNode.jjtGetChild(13) instanceof ASTInt);
        assertTrue(simplifiedNode.jjtGetChild(14) instanceof ASTDouble);
        assertEquals(Integer.toString(1), ((SimpleNode) simplifiedNode.jjtGetChild(0)).jjtGetValue());
        assertNull(((SimpleNode) simplifiedNode.jjtGetChild(1)).jjtGetValue());
        assertNull(((SimpleNode) simplifiedNode.jjtGetChild(2)).jjtGetValue());
        assertEquals(Integer.toString(41), ((SimpleNode) simplifiedNode.jjtGetChild(3)).jjtGetValue());
        assertEquals(Integer.toString(42), ((SimpleNode) simplifiedNode.jjtGetChild(4)).jjtGetValue());
        assertEquals(Double.toString(43.7), ((SimpleNode) simplifiedNode.jjtGetChild(5)).jjtGetValue());
        assertEquals(Integer.toString(44), ((SimpleNode) simplifiedNode.jjtGetChild(6)).jjtGetValue());
        assertEquals(Integer.toString(45), ((SimpleNode) simplifiedNode.jjtGetChild(7)).jjtGetValue());
        assertEquals(Integer.toString(46), ((SimpleNode) simplifiedNode.jjtGetChild(8)).jjtGetValue());
        assertEquals(Integer.toString(47), ((SimpleNode) simplifiedNode.jjtGetChild(9)).jjtGetValue());
        assertEquals(Integer.toString(48), ((SimpleNode) simplifiedNode.jjtGetChild(10)).jjtGetValue());
        assertNull(((SimpleNode) simplifiedNode.jjtGetChild(11)).jjtGetValue());
        assertEquals(Integer.toString(53), ((SimpleNode) simplifiedNode.jjtGetChild(12)).jjtGetValue());
        assertEquals(Integer.toString(54), ((SimpleNode) simplifiedNode.jjtGetChild(13)).jjtGetValue());
        assertEquals(Double.toString(55.56), ((SimpleNode) simplifiedNode.jjtGetChild(14)).jjtGetValue());
    }

    @Test
    public void simplifyBoolExpr_ok() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/boolExpr.mch"));
        SimpleNode expr = assertDoesNotThrow(BParser::parseExpr0);
        SimpleNode simplifiedNode = new ASTSimplifier().simplify(expr);
        assertEquals(3, simplifiedNode.jjtGetNumChildren());
        assertTrue(simplifiedNode instanceof ASTEquiv);
        assertTrue(simplifiedNode.jjtGetChild(0) instanceof ASTIdentifier);
        assertTrue(simplifiedNode.jjtGetChild(1) instanceof ASTImplies);
        assertTrue(simplifiedNode.jjtGetChild(2) instanceof ASTImplies);
        assertEquals("d", ((SimpleNode) simplifiedNode.jjtGetChild(0)).jjtGetValue());
    }

}