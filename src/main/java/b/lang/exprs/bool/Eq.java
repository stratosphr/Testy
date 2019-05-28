package b.lang.exprs.bool;

import b.lang.bobjectvisitors.IBObjectVisitor;
import b.lang.exprs.IExpr;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 23:33
 */
public final class Eq extends ABinaryBoolExpr implements IBoolExpr {

    public Eq(IExpr left, IExpr right) {
        super(left, right);
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
