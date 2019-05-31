package b.lang.exprs.arith;

import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.bobjectvisitors.primer.IExprPrimer;
import b.lang.exprs.AConst;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 17:30
 */
public final class RealConst extends AConst<IArithExpr, RealConst> implements IArithExpr<RealConst> {

    public RealConst(String name, IArithExpr value) {
        super(name, value);
    }

    @Override
    public RealConst accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

    @Override
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

}
