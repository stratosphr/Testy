package b.lang.exprs.bool;

import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.bobjectvisitors.primer.IExprPrimer;
import b.lang.exprs.AConst;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 17:33
 */
public class BoolConst extends AConst<IBoolExpr, BoolConst> implements IBoolExpr<BoolConst> {

    public BoolConst(String name, IBoolExpr value) {
        super(name, value);
    }

    @Override
    public BoolConst accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

    @Override
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

}
