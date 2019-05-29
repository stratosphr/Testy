package b.lang.exprs.arith;

import b.lang.bobjectvisitors.IBObjectVisitor;

/**
 * Created by gvoiron on 21/05/19.
 * Time : 15:21
 */
public final class Int extends ANumber<Integer> {

    public Int(int value) {
        super(value);
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
