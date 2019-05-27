package b.lang.defs;

import b.lang.bobjectvisitors.IBObjectVisitor;
import b.lang.exprs.ASymbol;
import b.lang.exprs.IExpr;
import b.lang.exprs.SymbolFactory;
import b.lang.types.AType;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 01:18
 */
public final class ConstDef extends ADef {

    private final IExpr value;

    public ConstDef(AType type, String name, IExpr value) {
        super(type, name);
        this.value = value;
    }

    public IExpr getValue() {
        return value;
    }

    public ASymbol getSymbol() {
        return SymbolFactory.buildConst(type, name, value);
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
