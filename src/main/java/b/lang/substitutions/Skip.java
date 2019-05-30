package b.lang.substitutions;

import b.lang.bobjectvisitors.formatter.IBFormatter;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 19:16
 */
public final class Skip extends ASubstitution {

    @Override
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

}
