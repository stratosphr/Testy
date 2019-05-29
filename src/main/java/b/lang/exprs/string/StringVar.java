package b.lang.exprs.string;

import b.lang.exprs.ASymbol;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 22:34
 */
public final class StringVar extends ASymbol implements IStringExpr {

    public StringVar(String name) {
        super(name);
    }

}
