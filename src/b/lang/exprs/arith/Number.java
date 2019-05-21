package b.lang.exprs.arith;

import b.lang.bobjectvisitors.IBObjectVisitor;

/**
 * Created by gvoiron on 05/05/19.
 * Time : 00:33
 */
public final class Number extends AArithExpr {

    private final double value;

    public Number(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
