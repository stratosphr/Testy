package b.lang.exprs;

import b.lang.bobjectvisitors.primer.IExprPrimer;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 22:51
 */
public final class FunCall extends ASymbol<FunCall> implements IExpr<FunCall> {

    public FunCall(String name) {
        super(name);
    }

    @Override
    public FunCall accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

}
