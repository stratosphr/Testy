package b.parser.astvisitors;

import b.parser.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by gvoiron on 06/05/19.
 * Time : 20:27
 */
class ASTSimplifierTest {

    @Test
    public void simplifyMachine_ok() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/machine.mch"));
        SimpleNode machine = assertDoesNotThrow(BParser::parseMachine);
        SimpleNode simplifiedMachine = new ASTSimplifier().simplify(machine);
        assertEquals(7, machine.jjtGetNumChildren());
        assertEquals(7, simplifiedMachine.jjtGetNumChildren());
    }

    @Test
    public void simplifySingleVarExpr_ok() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/singleVarExpr.mch"));
        SimpleNode expr = assertDoesNotThrow(BParser::parseExpr0);
        SimpleNode simplifiedExpr = new ASTSimplifier().simplify(expr);
        assertTrue(simplifiedExpr instanceof ASTIdentifier);
        assertEquals("var1", simplifiedExpr.jjtGetValue());
        assertEquals(3, simplifiedExpr.getSourceCoordinates().getLineStart());
        assertEquals(3, simplifiedExpr.getSourceCoordinates().getLineEnd());
        assertEquals(9, simplifiedExpr.getSourceCoordinates().getColumnStart());
        assertEquals(12, simplifiedExpr.getSourceCoordinates().getColumnEnd());
    }

    @Test
    public void simplifyArithExpr_ok() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/arithExpr.mch"));
        SimpleNode expr = assertDoesNotThrow(BParser::parseExpr0);
        SimpleNode simplifiedExpr = new ASTSimplifier().simplify(expr);
        assertEquals(16, simplifiedExpr.jjtGetNumChildren());
        assertTrue(simplifiedExpr instanceof ASTPlus);
        assertTrue(simplifiedExpr.jjtGetChild(0) instanceof ASTInt);
        assertTrue(simplifiedExpr.jjtGetChild(1) instanceof ASTTimes);
        assertTrue(simplifiedExpr.jjtGetChild(2) instanceof ASTMod);
        assertTrue(simplifiedExpr.jjtGetChild(3) instanceof ASTInt);
        assertTrue(simplifiedExpr.jjtGetChild(4) instanceof ASTInt);
        assertTrue(simplifiedExpr.jjtGetChild(5) instanceof ASTDouble);
        assertTrue(simplifiedExpr.jjtGetChild(6) instanceof ASTInt);
        assertTrue(simplifiedExpr.jjtGetChild(7) instanceof ASTInt);
        assertTrue(simplifiedExpr.jjtGetChild(8) instanceof ASTInt);
        assertTrue(simplifiedExpr.jjtGetChild(9) instanceof ASTInt);
        assertTrue(simplifiedExpr.jjtGetChild(10) instanceof ASTInt);
        assertTrue(simplifiedExpr.jjtGetChild(11) instanceof ASTUMinus);
        assertTrue(simplifiedExpr.jjtGetChild(12) instanceof ASTInt);
        assertTrue(simplifiedExpr.jjtGetChild(13) instanceof ASTInt);
        assertTrue(simplifiedExpr.jjtGetChild(14) instanceof ASTDouble);
        assertTrue(simplifiedExpr.jjtGetChild(15) instanceof ASTDouble);
        assertEquals(Integer.toString(1), ((SimpleNode) simplifiedExpr.jjtGetChild(0)).jjtGetValue());
        assertNull(((SimpleNode) simplifiedExpr.jjtGetChild(1)).jjtGetValue());
        assertNull(((SimpleNode) simplifiedExpr.jjtGetChild(2)).jjtGetValue());
        assertEquals(Integer.toString(41), ((SimpleNode) simplifiedExpr.jjtGetChild(3)).jjtGetValue());
        assertEquals(Integer.toString(42), ((SimpleNode) simplifiedExpr.jjtGetChild(4)).jjtGetValue());
        assertEquals(Double.toString(43.7), ((SimpleNode) simplifiedExpr.jjtGetChild(5)).jjtGetValue());
        assertEquals(Integer.toString(44), ((SimpleNode) simplifiedExpr.jjtGetChild(6)).jjtGetValue());
        assertEquals(Integer.toString(45), ((SimpleNode) simplifiedExpr.jjtGetChild(7)).jjtGetValue());
        assertEquals(Integer.toString(46), ((SimpleNode) simplifiedExpr.jjtGetChild(8)).jjtGetValue());
        assertEquals(Integer.toString(47), ((SimpleNode) simplifiedExpr.jjtGetChild(9)).jjtGetValue());
        assertEquals(Integer.toString(48), ((SimpleNode) simplifiedExpr.jjtGetChild(10)).jjtGetValue());
        assertNull(((SimpleNode) simplifiedExpr.jjtGetChild(11)).jjtGetValue());
        assertEquals(Integer.toString(53), ((SimpleNode) simplifiedExpr.jjtGetChild(12)).jjtGetValue());
        assertEquals(Integer.toString(54), ((SimpleNode) simplifiedExpr.jjtGetChild(13)).jjtGetValue());
        assertEquals(Double.toString(55.56), ((SimpleNode) simplifiedExpr.jjtGetChild(14)).jjtGetValue());
        assertEquals(Double.toString(56.0), ((SimpleNode) simplifiedExpr.jjtGetChild(15)).jjtGetValue());
    }

    @Test
    public void simplifyBoolExpr_ok() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/boolExpr.mch"));
        SimpleNode expr = assertDoesNotThrow(BParser::parseExpr0);
        SimpleNode simplifiedExpr = new ASTSimplifier().simplify(expr);
        System.out.println();
        simplifiedExpr.dump("");
        assertEquals(5, simplifiedExpr.jjtGetNumChildren());
        assertTrue(simplifiedExpr instanceof ASTEquiv);
        assertTrue(simplifiedExpr.jjtGetChild(0) instanceof ASTIdentifier);
        assertTrue(simplifiedExpr.jjtGetChild(1) instanceof ASTImplies);
        assertTrue(simplifiedExpr.jjtGetChild(2) instanceof ASTImplies);
        assertTrue(simplifiedExpr.jjtGetChild(3) instanceof ASTIn);
        assertTrue(simplifiedExpr.jjtGetChild(4) instanceof ASTNEq);
        assertEquals("d", ((SimpleNode) simplifiedExpr.jjtGetChild(0)).jjtGetValue());
    }

}