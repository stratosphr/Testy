package b.lang.substitutions;

import b.lang.bobjectvisitors.IBObjectVisitor;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 19:16
 */
public final class Skip extends ASubstitution {

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
