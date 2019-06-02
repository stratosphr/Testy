package b.lang.exprs.arith;

import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.bobjectvisitors.primer.IExprPrimer;
import b.lang.exprs.AAssignable;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 22:32
 */
public final class RealVar extends AAssignable<RealVar> implements IArithExpr<RealVar> {

    public RealVar(String name) {
        super(name);
    }

    @Override
    public RealVar accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

    @Override
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

}
