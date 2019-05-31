package b.lang;

import b.lang.bobjectvisitors.formatter.BFormatter;
import b.lang.bobjectvisitors.formatter.IBFormattable;

/**
 * Created by gvoiron on 04/05/19.
 * Time : 23:01
 */
public abstract class AObject implements IBFormattable {

    private String toDiscriminatingString() {
        return accept(new BFormatter(true));
    }

    @Override
    public final String toString() {
        return accept(new BFormatter(false));
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode() * toDiscriminatingString().hashCode();
    }

    @Override
    public final boolean equals(Object obj) {
        return obj == this || obj != null && getClass().equals(obj.getClass()) && hashCode() == obj.hashCode() && toDiscriminatingString().equals(((AObject) obj).toDiscriminatingString());
    }

}
