package b.lang.exprs.arith;

import java.util.Arrays;

/**
 * Created by gvoiron on 04/05/19.
 * Time : 22:58
 */
public final class Div extends ANaryArithExpr {

    public Div(AArithExpr... operands) {
        super(Arrays.asList(operands));
    }

}
