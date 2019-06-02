package b.lang.exprs.set;

import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.bobjectvisitors.primer.IExprPrimer;
import b.lang.exprs.AAssignable;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 22:33
 */
public final class SetVar extends AAssignable<SetVar> implements ISetExpr<SetVar> {

    public SetVar(String name) {
        super(name);
    }

    @Override
    public SetVar accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

    @Override
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

}
