/* Generated By:JJTree&JavaCC: Do not edit this line. BParserConstants.java */
package b.parser;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface BParserConstants {

    /**
     * End of File.
     */
    int EOF = 0;
    /**
     * RegularExpression Id.
     */
    int MACHINE = 5;
    /**
     * RegularExpression Id.
     */
    int MINUS = 6;
    /**
     * RegularExpression Id.
     */
    int PLUS = 7;
    /**
     * RegularExpression Id.
     */
    int TIMES = 8;
    /**
     * RegularExpression Id.
     */
    int DIV = 9;
    /**
     * RegularExpression Id.
     */
    int MOD = 10;
    /**
     * RegularExpression Id.
     */
    int NUMBER = 11;
    /**
     * RegularExpression Id.
     */
    int DIGIT = 12;
    /**
     * RegularExpression Id.
     */
    int ENUMVALUE = 13;
    /**
     * RegularExpression Id.
     */
    int IDENTIFIER = 14;

    /**
     * Lexical state.
     */
    int DEFAULT = 0;

    /**
     * Literal token values.
     */
    String[] tokenImage = {
            "<EOF>",
            "\" \"",
            "\"\\r\"",
            "\"\\t\"",
            "\"\\n\"",
            "\"MACHINE\"",
            "\"-\"",
            "\"+\"",
            "\"*\"",
            "\"/\"",
            "\"%\"",
            "<NUMBER>",
            "<DIGIT>",
            "<ENUMVALUE>",
            "<IDENTIFIER>",
            "\"(\"",
            "\")\"",
    };

}
