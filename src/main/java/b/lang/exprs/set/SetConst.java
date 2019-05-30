package b.lang.exprs.set;

import b.lang.bobjectvisitors.primer.IExprPrimer;
import b.lang.exprs.AConst;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 17:47
 */
public final class SetConst extends AConst<SetConst> implements ISetExpr<SetConst> {

    public SetConst(String name, ISetExpr value) {
        super(name, value);
    }

    @Override
    public SetConst accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

}
