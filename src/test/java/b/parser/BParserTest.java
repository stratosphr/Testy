package b.parser;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by gvoiron on 04/05/19.
 * Time : 10:40
 */
class BParserTest {

    @Test
    void setInputFile_fileNotFound() {
        assertThrows(FileNotFoundException.class, () -> BParser.setInputFile("/"));
    }

    @Test
    void setInputFile_fileFound() {
        assertDoesNotThrow(() -> BParser.setInputFile("src/test/resources/singleVarExpr.mch"));
        assertDoesNotThrow(() -> BParser.setInputFile("src/test/resources/arithExpr.mch"));
        assertDoesNotThrow(() -> BParser.setInputFile("src/test/resources/boolExpr.mch"));
        assertDoesNotThrow(() -> BParser.setInputFile("src/test/resources/machine.mch"));
    }

    @Test
    void parseExpr_noSyntaxError_arithExpr() {
        assertDoesNotThrow(() -> BParser.setInputFile("src/test/resources/arithExpr.mch"));
        assertDoesNotThrow(BParser::parseExpr);
    }

    @Test
    void parseExpr_noSyntaxError_boolExpr() {
        assertDoesNotThrow(() -> BParser.setInputFile("src/test/resources/boolExpr.mch"));
        assertDoesNotThrow(BParser::parseExpr);
    }

    @Test
    void parseMachine_noSyntaxError() {
        assertDoesNotThrow(() -> BParser.setInputFile("src/test/resources/machine.mch"));
        assertDoesNotThrow(BParser::parseMachine);
    }

}