package b.lang.exprs;

import b.formatters.BObjectVisitor;

/**
 * Created by gvoiron on 04/05/19.
 * Time : 23:55
 */
public final class Symbol extends AExpr {

    private String name;

    public Symbol(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String accept(BObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
