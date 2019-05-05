package b.lang.exprs.arith;

import b.formatters.BObjectVisitor;

/**
 * Created by gvoiron on 05/05/19.
 * Time : 00:33
 */
public final class Number extends AArithExpr {

    private double value;

    public Number(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String accept(BObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
