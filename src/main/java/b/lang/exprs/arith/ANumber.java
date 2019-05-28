package b.lang.exprs.arith;

import b.lang.AObject;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 18:14
 */
public abstract class ANumber<Type> extends AObject implements IArithExpr {

    private Type value;

    public ANumber(Type value) {
        this.value = value;
    }

    public final Type getValue() {
        return value;
    }

}
