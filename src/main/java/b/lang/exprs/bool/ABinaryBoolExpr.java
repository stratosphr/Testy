package b.lang.exprs.bool;

import b.lang.AObject;
import b.lang.exprs.IExpr;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 23:34
 */
public abstract class ABinaryBoolExpr<T extends ABinaryBoolExpr<T>> extends AObject implements IBoolExpr<T> {

    private final IExpr left;
    private final IExpr right;

    public ABinaryBoolExpr(IExpr left, IExpr right) {
        this.left = left;
        this.right = right;
    }

    public IExpr getLeft() {
        return left;
    }

    public IExpr getRight() {
        return right;
    }

}
