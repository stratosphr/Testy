package b.lang;

import b.formatters.BFormatter;
import b.formatters.BObjectFormattable;

/**
 * Created by gvoiron on 04/05/19.
 * Time : 23:01
 */
public abstract class ABObject implements BObjectFormattable {

    @Override
    public final String toString() {
        return accept(new BFormatter());
    }

}
