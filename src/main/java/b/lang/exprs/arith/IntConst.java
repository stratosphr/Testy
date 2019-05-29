package b.lang.exprs.arith;

import b.lang.exprs.AConst;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 17:29
 */
public final class IntConst extends AConst implements IArithExpr {

    public IntConst(String name, IArithExpr value) {
        super(name, value);
    }

}
