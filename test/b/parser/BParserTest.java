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
        assertDoesNotThrow(() -> BParser.setInputFile("res/singleVarExpr.mch"));
        assertDoesNotThrow(() -> BParser.setInputFile("res/arithExpr.mch"));
        assertDoesNotThrow(() -> BParser.setInputFile("res/boolExpr.mch"));
        assertDoesNotThrow(() -> BParser.setInputFile("res/machine.mch"));
    }

    @Test
    void parseExpr_noSyntaxError_arithExpr() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/arithExpr.mch"));
        assertDoesNotThrow(BParser::parseExpr0);
    }

    @Test
    void parseExpr_noSyntaxError_boolExpr() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/boolExpr.mch"));
        assertDoesNotThrow(BParser::parseExpr0);
    }

}