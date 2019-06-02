package b.lang.substitutions;

import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.exprs.AAssignable;
import b.lang.exprs.bool.IBoolExpr;
import b.lang.exprs.bool.True;

import java.util.Set;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 19:16
 */
public final class Skip extends ASubstitution {

    @Override
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

    @Override
    public IBoolExpr getPrd(Set<AAssignable> assignables) {
        return new True();
    }

}
