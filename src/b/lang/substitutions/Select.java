package b.lang.substitutions;

import b.lang.bobjectvisitors.IBObjectVisitor;
import b.lang.exprs.bool.IBoolExpr;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 23:37
 */
public final class Select extends ASubstitution {

    private final IBoolExpr condition;
    private final ASubstitution substitution;

    public Select(IBoolExpr condition, ASubstitution substitution) {
        this.condition = condition;
        this.substitution = substitution;
    }

    public IBoolExpr getCondition() {
        return condition;
    }

    public ASubstitution getSubstitution() {
        return substitution;
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
