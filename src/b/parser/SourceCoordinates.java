package b.parser;

/**
 * Created by gvoiron on 04/05/19.
 * Time : 21:02
 */
public final class SourceCoordinates {

    private final String file;
    private final int lineStart;
    private final int lineEnd;
    private final int columnStart;
    private final int columnEnd;

    public SourceCoordinates(String file, int lineStart, int lineEnd, int columnStart, int columnEnd) {
        this.file = file;
        this.lineStart = lineStart;
        this.lineEnd = lineEnd;
        this.columnStart = columnStart;
        this.columnEnd = columnEnd;
    }

    public String getFile() {
        return file;
    }

    public int getLineStart() {
        return lineStart;
    }

    public int getLineEnd() {
        return lineEnd;
    }

    public int getColumnStart() {
        return columnStart;
    }

    public int getColumnEnd() {
        return columnEnd;
    }

}
