package b.lang.exprs.bool;

import b.lang.bobjectvisitors.primer.IExprPrimer;
import b.lang.exprs.ASymbol;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 22:32
 */
public final class BoolVar extends ASymbol<BoolVar> implements IBoolExpr<BoolVar> {

    public BoolVar(String name) {
        super(name);
    }

    @Override
    public BoolVar accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

}
