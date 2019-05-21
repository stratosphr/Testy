package b.lang.exprs.arith;

import b.lang.bobjectvisitors.IBObjectVisitor;

/**
 * Created by gvoiron on 05/05/19.
 * Time : 00:22
 */
public final class ArithVar extends AArithExpr {

    private final String name;

    public ArithVar(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
