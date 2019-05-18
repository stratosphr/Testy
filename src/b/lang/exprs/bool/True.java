package b.lang.exprs.bool;

import b.lang.bobjectvisitors.IBObjectVisitor;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 02:23
 */
public final class True extends ABoolExpr {

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
