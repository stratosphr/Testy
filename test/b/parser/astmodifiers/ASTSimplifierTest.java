package b.parser.astmodifiers;

import b.parser.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by gvoiron on 06/05/19.
 * Time : 20:27
 */
class ASTSimplifierTest {

    private String singleVarToString;

    @BeforeEach
    void setUp() {
        singleVarToString = "Identifier[var1](" + ASTIdentifier.class + ", 3, 9)";
    }

    @Test
    public void simplifySingleVarExpr_ok() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/singleVarExpr.mch"));
        SimpleNode expr = assertDoesNotThrow(BParser::parseExpr0);
        SimpleNode simplifiedNode = new ASTSimplifier().simplify(expr);
        assertEquals(singleVarToString, simplifiedNode.toString());
    }

    @Test
    public void simplifyArithExpr_ok() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/arithExpr.mch"));
        SimpleNode expr = assertDoesNotThrow(BParser::parseExpr0);
        SimpleNode simplifiedNode = new ASTSimplifier().simplify(expr);
        simplifiedNode.dump("");
        assertEquals(14, simplifiedNode.jjtGetNumChildren());
        assertTrue(simplifiedNode.jjtGetChild(0) instanceof ASTNumber);
        assertTrue(simplifiedNode.jjtGetChild(1) instanceof ASTTimes);
        assertTrue(simplifiedNode.jjtGetChild(2) instanceof ASTMod);
        assertTrue(simplifiedNode.jjtGetChild(3) instanceof ASTNumber);
        assertTrue(simplifiedNode.jjtGetChild(4) instanceof ASTNumber);
        assertTrue(simplifiedNode.jjtGetChild(5) instanceof ASTNumber);
        assertTrue(simplifiedNode.jjtGetChild(6) instanceof ASTNumber);
        assertTrue(simplifiedNode.jjtGetChild(7) instanceof ASTNumber);
        assertTrue(simplifiedNode.jjtGetChild(8) instanceof ASTNumber);
        assertTrue(simplifiedNode.jjtGetChild(9) instanceof ASTNumber);
        assertTrue(simplifiedNode.jjtGetChild(10) instanceof ASTNumber);
        assertTrue(simplifiedNode.jjtGetChild(11) instanceof ASTUMinus);
        assertTrue(simplifiedNode.jjtGetChild(12) instanceof ASTNumber);
        assertTrue(simplifiedNode.jjtGetChild(13) instanceof ASTNumber);
        assertEquals(Integer.toString(1), ((SimpleNode) simplifiedNode.jjtGetChild(0)).jjtGetValue());
        assertNull(((SimpleNode) simplifiedNode.jjtGetChild(1)).jjtGetValue());
        assertNull(((SimpleNode) simplifiedNode.jjtGetChild(2)).jjtGetValue());
        assertEquals(Integer.toString(41), ((SimpleNode) simplifiedNode.jjtGetChild(3)).jjtGetValue());
        assertEquals(Integer.toString(42), ((SimpleNode) simplifiedNode.jjtGetChild(4)).jjtGetValue());
        assertEquals(Integer.toString(43), ((SimpleNode) simplifiedNode.jjtGetChild(5)).jjtGetValue());
        assertEquals(Integer.toString(44), ((SimpleNode) simplifiedNode.jjtGetChild(6)).jjtGetValue());
        assertEquals(Integer.toString(45), ((SimpleNode) simplifiedNode.jjtGetChild(7)).jjtGetValue());
        assertEquals(Integer.toString(46), ((SimpleNode) simplifiedNode.jjtGetChild(8)).jjtGetValue());
        assertEquals(Integer.toString(47), ((SimpleNode) simplifiedNode.jjtGetChild(9)).jjtGetValue());
        assertEquals(Integer.toString(48), ((SimpleNode) simplifiedNode.jjtGetChild(10)).jjtGetValue());
        assertNull(((SimpleNode) simplifiedNode.jjtGetChild(11)).jjtGetValue());
        assertEquals(Integer.toString(53), ((SimpleNode) simplifiedNode.jjtGetChild(12)).jjtGetValue());
        assertEquals(Integer.toString(54), ((SimpleNode) simplifiedNode.jjtGetChild(13)).jjtGetValue());
        simplifiedNode.dump("");
    }

}