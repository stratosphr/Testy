package b.lang.exprs.arith;

import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.bobjectvisitors.primer.IExprPrimer;

/**
 * Created by gvoiron on 21/05/19.
 * Time : 15:21
 */
public final class Real extends ANumber<Double> {

    public Real(double value) {
        super(value);
    }

    @Override
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

    @Override
    public ANumber<Double> accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

}
