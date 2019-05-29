package b.lang.exprs.bool;

import b.lang.AObject;
import b.lang.exprs.IExpr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by gvoiron on 30/05/19.
 * Time : 00:44
 */
public abstract class ANaryBoolExpr extends AObject implements IBoolExpr {

    private final List<IExpr> operands;

    public ANaryBoolExpr(Collection<IExpr> operands) {
        this.operands = new ArrayList<>(operands);
    }

    public List<IExpr> getOperands() {
        return operands;
    }

}
