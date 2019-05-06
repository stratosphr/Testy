package b.parser;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

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
        assertAll(
                () -> assertDoesNotThrow(() -> BParser.setInputFile("res/machine.mch")),
                () -> assertDoesNotThrow(() -> BParser.setInputFile("res/arithExpr.mch"))
        );
    }

    @Test
    void parseExpr_noSyntaxError_arithexpr() {
        assertDoesNotThrow(() -> BParser.setInputFile("res/arithExpr.mch"));
        assertDoesNotThrow(BParser::parseExpr0);
    }

}