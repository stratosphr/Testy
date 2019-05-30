package b.lang.exprs.arith;

import b.lang.bobjectvisitors.primer.IExprPrimer;
import b.lang.exprs.AConst;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 17:29
 */
public final class IntConst extends AConst<IntConst> implements IArithExpr<IntConst> {

    public IntConst(String name, IArithExpr value) {
        super(name, value);
    }

    @Override
    public IntConst accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

}
