package b.parser.astvisitors;

import b.parser.SourceCoordinates;

/**
 * Created by gvoiron on 29/05/19.
 * Time : 18:23
 */
public class ShouldNotHappenError extends Error {

    public ShouldNotHappenError(SourceCoordinates coordinates, String error) {
        super("l." + coordinates.getLineStart() + ", c." + coordinates.getColumnStart() + ": " + error);
    }

}
