package b.lang;

import b.lang.bobjectvisitors.BFormatter;
import b.lang.bobjectvisitors.BObjectFormattable;

/**
 * Created by gvoiron on 04/05/19.
 * Time : 23:01
 */
public abstract class AObject implements BObjectFormattable {

    @Override
    public final String toString() {
        return accept(new BFormatter());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode() * toString().hashCode();
    }

    @Override
    public final boolean equals(Object obj) {
        return obj == this || obj != null && getClass().equals(obj.getClass()) && hashCode() == obj.hashCode() && toString().equals(obj.toString());
    }

}
