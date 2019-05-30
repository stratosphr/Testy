package b.lang.exprs.bool;

import b.lang.AObject;
import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.bobjectvisitors.primer.IExprPrimer;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 02:23
 */
public final class True extends AObject implements IBoolExpr<True> {

    @Override
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

    @Override
    public True accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

}
