package b.parser;

import b.lang.exprs.arith.Plus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by gvoiron on 05/05/19.
 * Time : 01:15
 */
class ASTAnalyzerTest {

    @BeforeEach
    void setUp() {
        setInputFile_fileFound();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void setInputFile_fileFound() {
        assertAll(
                () -> assertDoesNotThrow(() -> BParser.setInputFile("res/machine.mch")),
                () -> assertDoesNotThrow(() -> BParser.setInputFile("res/arithExpr.mch"))
        );
    }

    @Test
    void visitArithExpr_correctAnalyze() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/arithExpr.mch"));
        SimpleNode expr = assertDoesNotThrow(BParser::parseExpr0);
        assertTrue(expr.jjtAccept(new ASTAnalyzer(), null) instanceof Plus);
    }

}