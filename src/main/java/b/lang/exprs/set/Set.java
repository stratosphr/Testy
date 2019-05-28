package b.lang.exprs.set;

import b.lang.AObject;
import b.lang.bobjectvisitors.IBObjectVisitor;
import b.lang.exprs.IExpr;

import java.util.LinkedHashSet;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 18:39
 */
public final class Set extends AObject implements ISetExpr {

    private LinkedHashSet<IExpr> elements;

    public Set(LinkedHashSet<IExpr> elements) {
        this.elements = elements;
    }

    public LinkedHashSet<IExpr> getElements() {
        return elements;
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
