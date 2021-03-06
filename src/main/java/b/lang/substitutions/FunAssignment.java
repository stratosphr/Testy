package b.lang.substitutions;

import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.exprs.AAssignable;
import b.lang.exprs.ASymbol;
import b.lang.exprs.IExpr;
import b.lang.exprs.bool.IBoolExpr;

import java.util.Set;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 22:38
 */
public final class FunAssignment extends AAssignment {

    private final ASymbol fun;
    private final IExpr parameter;
    private final IExpr value;

    public FunAssignment(ASymbol fun, IExpr parameter, IExpr value) {
        this.fun = fun;
        this.parameter = parameter;
        this.value = value;
    }

    public ASymbol getFun() {
        return fun;
    }

    public IExpr getParameter() {
        return parameter;
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
        return null;
    }

}
