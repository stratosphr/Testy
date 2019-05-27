package b.lang.exprs.arith;

import b.lang.bobjectvisitors.IBObjectVisitor;

import java.util.Arrays;

/**
 * Created by gvoiron on 04/05/19.
 * Time : 22:58
 */
public final class Minus extends ANaryArithExpr {

    public Minus(IArithExpr... operands) {
        super(Arrays.asList(operands));
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
