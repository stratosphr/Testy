package b.lang.substitutions;

import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.bobjectvisitors.primer.ExprPrimer;
import b.lang.exprs.AAssignable;
import b.lang.exprs.ASymbol;
import b.lang.exprs.IExpr;
import b.lang.exprs.bool.And;
import b.lang.exprs.bool.Eq;
import b.lang.exprs.bool.IBoolExpr;

import java.util.Set;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 22:26
 */
public final class VarAssignment extends AAssignment {

    private final ASymbol var;
    private final IExpr value;

    public VarAssignment(ASymbol var, IExpr value) {
        this.var = var;
        this.value = value;
    }

    public ASymbol getVar() {
        return var;
    }

    public IExpr getValue() {
        return value;
    }

    @Override
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

    @Override
    public IBoolExpr getPrd(Set<AAssignable> assignables) {
        return new And(new Eq(var.accept(new ExprPrimer()), value));
    }

}
