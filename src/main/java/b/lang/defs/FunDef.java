package b.lang.defs;

import b.lang.bobjectvisitors.IBObjectVisitor;
import b.lang.exprs.ASymbol;
import b.lang.exprs.SymbolFactory;
import b.lang.exprs.set.ISetExpr;
import b.lang.types.AType;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 01:32
 */
public final class FunDef extends ADef {

    private final AType coType;
    private final ISetExpr domain;
    private final ISetExpr coDomain;

    public FunDef(AType type, AType coType, String name, ISetExpr domain, ISetExpr coDomain) {
        super(type, name);
        this.coType = coType;
        this.domain = domain;
        this.coDomain = coDomain;
    }

    public AType getCoType() {
        return coType;
    }

    public ISetExpr getDomain() {
        return domain;
    }

    public ISetExpr getCoDomain() {
        return coDomain;
    }

    @Override
    public ASymbol getSymbol() {
        return SymbolFactory.buildFunCall(name);
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
