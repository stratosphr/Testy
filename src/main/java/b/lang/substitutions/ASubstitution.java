package b.lang.substitutions;

import b.lang.AObject;
import b.lang.exprs.AAssignable;
import b.lang.exprs.bool.IBoolExpr;

import java.util.Set;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 19:14
 */
public abstract class ASubstitution extends AObject {

    public abstract IBoolExpr getPrd(Set<AAssignable> assignables);

}
