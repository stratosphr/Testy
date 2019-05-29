package b.lang.exprs.string;

import b.lang.exprs.AConst;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 17:50
 */
public final class StringConst extends AConst implements IStringExpr {

    public StringConst(String name, IStringExpr value) {
        super(name, value);
    }

}
