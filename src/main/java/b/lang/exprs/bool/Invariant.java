package b.lang.exprs.bool;

import b.lang.AObject;
import b.lang.bobjectvisitors.IBObjectVisitor;

/**
 * Created by gvoiron on 28/05/19.
 * Time : 00:12
 */
public final class Invariant extends AObject implements IBoolExpr {

    private IBoolExpr expr;

    public Invariant(IBoolExpr expr) {
        this.expr = expr;
    }

    public IBoolExpr getExpr() {
        return expr;
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
