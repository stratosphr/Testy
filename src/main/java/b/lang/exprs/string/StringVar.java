package b.lang.exprs.string;

import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.bobjectvisitors.primer.IExprPrimer;
import b.lang.exprs.AAssignable;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 22:34
 */
public final class StringVar extends AAssignable<StringVar> implements IStringExpr<StringVar> {

    public StringVar(String name) {
        super(name);
    }

    @Override
    public StringVar accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

    @Override
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

}
