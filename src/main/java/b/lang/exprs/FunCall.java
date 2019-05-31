package b.lang.exprs;

import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.bobjectvisitors.primer.IExprPrimer;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 22:51
 */
public final class FunCall extends ASymbol<FunCall> implements IExpr<FunCall> {

    private IExpr operand;

    public FunCall(String name, IExpr operand) {
        super(name);
        this.operand = operand;
    }

    public IExpr getOperand() {
        return operand;
    }

    @Override
    public FunCall accept(IExprPrimer visitor) {
        return visitor.visit(this);
    }

    @Override
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

}
