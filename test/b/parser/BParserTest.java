package b.parser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by gvoiron on 04/05/19.
 * Time : 10:40
 */
class BParserTest {

    @BeforeEach
    void setUp() {
        setInputFile_fileFound();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void setInputFile_fileNotFound() {
        assertThrows(FileNotFoundException.class, () -> BParser.setInputFile("/"));
    }

    @Test
    void setInputFile_fileFound() {
        assertAll(
                () -> assertDoesNotThrow(() -> BParser.setInputFile("res/machine.mch")),
                () -> assertDoesNotThrow(() -> BParser.setInputFile("res/arithExpr.mch"))
        );
    }

    @Test
    void parseMachine_noSyntaxError() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/machine.mch"));
        assertDoesNotThrow(BParser::parseMachine);
    }

    @Test
    void parseMachine_noSyntaxError_correctAST() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/machine.mch"));
        SimpleNode machine = assertDoesNotThrow(BParser::parseMachine);
        assertEquals("name", machine.jjtGetValue());
    }

    @Test
    void parseExpr_noSyntaxError_arithexpr() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/arithExpr.mch"));
        SimpleNode simpleNode = assertDoesNotThrow(BParser::parseExpr);
        simpleNode.dump("");
    }

    @Test
    void parseExpr_noSyntaxError_correctAST() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/arithExpr.mch"));
        assertDoesNotThrow(BParser::parseExpr);
    }

}