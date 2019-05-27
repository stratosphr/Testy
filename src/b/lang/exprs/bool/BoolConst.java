package b.lang.exprs.bool;

import b.lang.exprs.AConst;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 17:33
 */
public class BoolConst extends AConst implements IBoolExpr {

    public BoolConst(String name, IBoolExpr value) {
        super(name, value);
    }

}
