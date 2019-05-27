package b.lang.exprs.bool;

import b.lang.exprs.AConst;
import b.lang.exprs.set.ISetExpr;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 17:47
 */
public final class SetConst extends AConst implements ISetExpr {

    public SetConst(String name, ISetExpr value) {
        super(name, value);
    }

}
