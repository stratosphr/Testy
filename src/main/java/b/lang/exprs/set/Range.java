package b.lang.exprs.set;

import b.lang.AObject;
import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.bobjectvisitors.primer.IExprPrimer;
import b.lang.exprs.arith.IArithExpr;

/**
 * Created by gvoiron on 21/05/19.
 * Time : 15:40
 */
public final class Range extends AObject implements ISetExpr<Range> {

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
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

    @Override
    public Range accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

}
