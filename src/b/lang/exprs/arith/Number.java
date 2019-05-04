package b.lang.exprs.arith;

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

}
