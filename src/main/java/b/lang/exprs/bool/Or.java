package b.lang.exprs.bool;

import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.bobjectvisitors.primer.IExprPrimer;

import java.util.Arrays;
import java.util.LinkedHashSet;

/**
 * Created by gvoiron on 30/05/19.
 * Time : 00:44
 */
public class Or extends ANaryBoolExpr<Or> {

    public Or(IBoolExpr... operands) {
        super(new LinkedHashSet<>(Arrays.asList(operands)));
    }

    @Override
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

    @Override
    public Or accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

}
