package b.lang.defs;

import b.bobjectvisitors.IBObjectVisitor;
import b.lang.Symbol;
import b.lang.exprs.AExpr;
import b.lang.types.AType;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 01:18
 */
public final class ConstDef extends ADef {

    private final AType type;
    private final Symbol name;
    private final AExpr value;

    public ConstDef(AType type, Symbol name, AExpr value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public AType getType() {
        return type;
    }

    public Symbol getSymbol() {
        return name;
    }

    public AExpr getValue() {
        return value;
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
