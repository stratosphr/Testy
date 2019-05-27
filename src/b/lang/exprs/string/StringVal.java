package b.lang.exprs.string;

import b.lang.AObject;
import b.lang.bobjectvisitors.IBObjectVisitor;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 18:51
 */
public final class StringVal extends AObject implements IStringExpr {

    private String value;

    public StringVal(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
