package b.lang.defs;

import b.lang.ASymbol;
import b.lang.SymbolFactory;
import b.lang.bobjectvisitors.IBObjectVisitor;
import b.lang.exprs.set.ISetExpr;
import b.lang.types.AType;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 01:31
 */
public final class VarDef extends ADef {

    private final ISetExpr domain;

    public VarDef(AType type, String name, ISetExpr domain) {
        super(type, name);
        this.domain = domain;
    }

    @Override
    public ASymbol getSymbol() {
        return SymbolFactory.buildVar(type, name);
    }

    public ISetExpr getDomain() {
        return domain;
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
