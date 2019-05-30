package b.lang.exprs.arith;

import b.lang.AObject;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 18:14
 */
public abstract class ANumber<T> extends AObject implements IArithExpr<ANumber<T>> {

    private T value;

    public ANumber(T value) {
        this.value = value;
    }

    public final T getValue() {
        return value;
    }

}
