package b.lang.exprs;

import b.lang.bobjectvisitors.formatter.IBFormattable;
import b.lang.bobjectvisitors.primer.IExprPrimable;

/**
 * Created by gvoiron on 04/05/19.
 * Time : 23:00
 */
public interface IExpr<T extends IExpr<T>> extends IBFormattable, IExprPrimable<T> {

}
