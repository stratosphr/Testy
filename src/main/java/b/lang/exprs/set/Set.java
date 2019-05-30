package b.lang.exprs.set;

import b.lang.AObject;
import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.bobjectvisitors.primer.IExprPrimer;
import b.lang.exprs.IExpr;

import java.util.LinkedHashSet;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 18:39
 */
public final class Set extends AObject implements ISetExpr<Set> {

    private java.util.Set<IExpr> elements;

    public Set(java.util.Set<IExpr> elements) {
        this.elements = new LinkedHashSet<>(elements);
    }

    public java.util.Set<IExpr> getElements() {
        return elements;
    }

    @Override
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

    @Override
    public Set accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

}
