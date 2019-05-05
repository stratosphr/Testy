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

    @Override
    public final int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || (obj instanceof BObjectFormattable && hashCode() == hashCode() && obj.toString().equals(toString()));
    }

}
