package b.lang.exprs.arith;

import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.bobjectvisitors.primer.IExprPrimer;

/**
 * Created by gvoiron on 21/05/19.
 * Time : 15:21
 */
public final class Int extends ANumber<Integer> {

    public Int(int value) {
        super(value);
    }

    @Override
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

    @Override
    public ANumber<Integer> accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

}
