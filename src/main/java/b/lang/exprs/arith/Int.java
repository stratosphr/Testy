package b.lang.exprs.arith;

import b.lang.AObject;
import b.lang.bobjectvisitors.IBObjectVisitor;

/**
 * Created by gvoiron on 21/05/19.
 * Time : 15:21
 */
public final class Int extends AObject implements IValue<Integer>, IArithExpr {

    private int value;

    public Int(int value) {
        this.value = value;
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Integer getValue() {
        return value;
    }

}
