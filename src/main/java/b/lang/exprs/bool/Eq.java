package b.lang.exprs.bool;

import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.bobjectvisitors.primer.IExprPrimer;
import b.lang.exprs.IExpr;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 23:33
 */
public final class Eq extends ABinaryBoolExpr {

    public Eq(IExpr left, IExpr right) {
        super(left, right);
    }

    @Override
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

    @Override
    public Eq accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

}
