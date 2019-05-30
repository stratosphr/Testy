package b.lang.defs;

import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.exprs.ASymbol;
import b.lang.exprs.SymbolFactory;
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
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

}
