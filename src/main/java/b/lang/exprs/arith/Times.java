package b.lang.exprs.arith;

import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.bobjectvisitors.primer.IExprPrimer;

import java.util.Arrays;

/**
 * Created by gvoiron on 04/05/19.
 * Time : 22:58
 */
public final class Times extends ANaryArithExpr<Times> {

    public Times(IArithExpr... operands) {
        super(Arrays.asList(operands));
    }

    @Override
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

    @Override
    public Times accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

}
