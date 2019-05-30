package b.lang.exprs.arith;

import b.lang.bobjectvisitors.primer.IExprPrimer;
import b.lang.exprs.AConst;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 17:30
 */
public final class RealConst extends AConst<RealConst> implements IArithExpr<RealConst> {

    public RealConst(String name, IArithExpr value) {
        super(name, value);
    }

    @Override
    public RealConst accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

}
