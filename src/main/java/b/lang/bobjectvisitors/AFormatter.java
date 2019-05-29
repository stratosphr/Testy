package b.lang.bobjectvisitors;

import java.util.Collections;

/**
 * Created by gvoiron on 05/05/19.
 * Time : 01:39
 */
public abstract class AFormatter {

    private int indentation;

    public AFormatter() {
        this.indentation = 0;
    }

    protected String indentLeft() {
        if (indentation > 0) {
            --indentation;
        }
        return "";
    }

    protected String indentRight() {
        ++indentation;
        return "";
    }

    protected String indent(String text) {
        return String.join("", Collections.nCopies(indentation, "\t")) + text;
    }

    protected String indentLine(String line) {
        return indent("") + line(line);
    }

    protected String line(String line) {
        return line + "\n";
    }

}
