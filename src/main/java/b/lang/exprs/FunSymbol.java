package b.lang.exprs;

import b.lang.bobjectvisitors.primer.IExprPrimer;

/**
 * Created by gvoiron on 31/05/19.
 * Time : 02:00
 */
public final class FunSymbol extends ASymbol<FunSymbol> {

    public FunSymbol(String name) {
        super(name);
    }

    @Override
    public FunSymbol accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

}
