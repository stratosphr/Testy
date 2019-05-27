package b.lang.exprs.arith;

import b.lang.AObject;
import b.lang.exprs.IExpr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by gvoiron on 05/05/19.
 * Time : 00:38
 */
public abstract class ANaryArithExpr extends AObject implements IArithExpr {

    private final List<IExpr> operands;

    public ANaryArithExpr(Collection<IExpr> operands) {
        this.operands = new ArrayList<>(operands);
    }

    public List<IExpr> getOperands() {
        return operands;
    }

}
