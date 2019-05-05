package b.lang.exprs.arith;

import b.formatters.BObjectVisitor;

/**
 * Created by gvoiron on 05/05/19.
 * Time : 00:22
 */
public final class ArithVar extends AArithExpr {

    private String name;

    public ArithVar(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String accept(BObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
