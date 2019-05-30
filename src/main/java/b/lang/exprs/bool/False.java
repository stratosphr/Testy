package b.lang.exprs.bool;

import b.lang.AObject;
import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.bobjectvisitors.primer.IExprPrimer;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 02:23
 */
public final class False extends AObject implements IBoolExpr<False> {

    @Override
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

    @Override
    public False accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

}
