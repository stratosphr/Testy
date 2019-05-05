package b.lang.exprs.arith;

import b.formatters.BObjectVisitor;

import java.util.Arrays;

/**
 * Created by gvoiron on 04/05/19.
 * Time : 22:58
 */
public final class Mod extends ANaryArithExpr {

    public Mod(AArithExpr... operands) {
        super(Arrays.asList(operands));
    }

    @Override
    public String accept(BObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
