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
    int EQUIV = 6;
    /**
     * RegularExpression Id.
     */
    int IMPLIES = 7;
    /**
     * RegularExpression Id.
     */
    int OR = 8;
    /**
     * RegularExpression Id.
     */
    int AND = 9;
    /**
     * RegularExpression Id.
     */
    int EQ = 10;
    /**
     * RegularExpression Id.
     */
    int NOT = 11;
    /**
     * RegularExpression Id.
     */
    int NEQ = 12;
    /**
     * RegularExpression Id.
     */
    int LT = 13;
    /**
     * RegularExpression Id.
     */
    int LE = 14;
    /**
     * RegularExpression Id.
     */
    int GT = 15;
    /**
     * RegularExpression Id.
     */
    int GE = 16;
    /**
     * RegularExpression Id.
     */
    int IN = 17;
    /**
     * RegularExpression Id.
     */
    int FALSE = 18;
    /**
     * RegularExpression Id.
     */
    int TRUE = 19;
    /**
     * RegularExpression Id.
     */
    int MINUS = 20;
    /**
     * RegularExpression Id.
     */
    int PLUS = 21;
    /**
     * RegularExpression Id.
     */
    int TIMES = 22;
    /**
     * RegularExpression Id.
     */
    int DIV = 23;
    /**
     * RegularExpression Id.
     */
    int MOD = 24;
    /**
     * RegularExpression Id.
     */
    int NUMBER = 25;
    /**
     * RegularExpression Id.
     */
    int DIGIT = 26;
    /**
     * RegularExpression Id.
     */
    int ENUMVALUE = 27;
    /**
     * RegularExpression Id.
     */
    int IDENTIFIER = 28;

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
            "<EQUIV>",
            "<IMPLIES>",
            "\"||\"",
            "\"&&\"",
            "\"=\"",
            "\"~\"",
            "<NEQ>",
            "\"<\"",
            "\"<=\"",
            "\">\"",
            "\">=\"",
            "\":\"",
            "\"false\"",
            "\"true\"",
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
