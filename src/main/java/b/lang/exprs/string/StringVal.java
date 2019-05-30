package b.lang.exprs.string;

import b.lang.AObject;
import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.bobjectvisitors.primer.IExprPrimer;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 18:51
 */
public final class StringVal extends AObject implements IStringExpr<StringVal> {

    private String value;

    public StringVal(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

    @Override
    public StringVal accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

}
