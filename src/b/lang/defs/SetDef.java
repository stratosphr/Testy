package b.lang.defs;

import b.lang.ASymbol;
import b.lang.SymbolFactory;
import b.lang.bobjectvisitors.IBObjectVisitor;
import b.lang.exprs.set.ISetExpr;
import b.lang.types.SetType;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 01:31
 */
public final class SetDef extends ADef {

    private final ISetExpr value;

    public SetDef(SetType type, String name, ISetExpr value) {
        super(type, name);
        this.value = value;
    }

    public ISetExpr getValue() {
        return value;
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public ASymbol getSymbol() {
        return SymbolFactory.buildSet((SetType) type, name, value);
    }

}
