package b.lang.bobjectvisitors.primer;

import b.lang.exprs.IExpr;

/**
 * Created by gvoiron on 30/05/19.
 * Time : 02:14
 */
public interface IExprPrimable<T extends IExpr<T>> {

    T accept(IExprPrimer visitor);

}
