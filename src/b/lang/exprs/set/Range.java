package b.lang.exprs.set;

import b.lang.AObject;
import b.lang.bobjectvisitors.IBObjectVisitor;
import b.lang.exprs.arith.IArithExpr;

/**
 * Created by gvoiron on 21/05/19.
 * Time : 15:40
 */
public final class Range extends AObject implements ISetExpr {

    private final IArithExpr lowerBound;
    private final IArithExpr upperBound;

    public Range(IArithExpr lowerBound, IArithExpr upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public IArithExpr getLowerBound() {
        return lowerBound;
    }

    public IArithExpr getUpperBound() {
        return upperBound;
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
