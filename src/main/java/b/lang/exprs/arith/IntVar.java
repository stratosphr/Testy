package b.lang.exprs.arith;

import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.bobjectvisitors.primer.IExprPrimer;
import b.lang.exprs.AAssignable;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 22:30
 */
public final class IntVar extends AAssignable<IntVar> implements IArithExpr<IntVar> {

    public IntVar(String name) {
        super(name);
    }

    @Override
    public IntVar accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

    @Override
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

}
