package b.lang.substitutions;

import b.lang.bobjectvisitors.IBObjectVisitor;
import b.lang.exprs.ASymbol;
import b.lang.exprs.IExpr;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 22:26
 */
public final class VarAssignment extends ASubstitution {

    private final ASymbol var;
    private final IExpr expr;

    public VarAssignment(ASymbol var, IExpr expr) {
        this.var = var;
        this.expr = expr;
    }

    public ASymbol getVar() {
        return var;
    }

    public IExpr getExpr() {
        return expr;
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
