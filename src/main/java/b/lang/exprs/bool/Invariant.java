package b.lang.exprs.bool;

import b.lang.AObject;
import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.bobjectvisitors.primer.IExprPrimer;

/**
 * Created by gvoiron on 28/05/19.
 * Time : 00:12
 */
public final class Invariant extends AObject implements IBoolExpr<Invariant> {

    private IBoolExpr expr;

    public Invariant(IBoolExpr expr) {
        this.expr = expr;
    }

    public IBoolExpr getExpr() {
        return expr;
    }

    @Override
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

    @Override
    public Invariant accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

}
