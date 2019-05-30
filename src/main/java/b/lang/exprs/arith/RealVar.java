package b.lang.exprs.arith;

import b.lang.bobjectvisitors.primer.IExprPrimer;
import b.lang.exprs.ASymbol;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 22:32
 */
public final class RealVar extends ASymbol<RealVar> implements IArithExpr<RealVar> {

    public RealVar(String name) {
        super(name);
    }

    @Override
    public RealVar accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

}
