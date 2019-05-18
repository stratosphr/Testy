package b.lang.exprs.arith;

import b.lang.AObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by gvoiron on 05/05/19.
 * Time : 00:38
 */
public abstract class ANaryArithExpr extends AArithExpr {

    private final List<AObject> operands;

    public ANaryArithExpr(Collection<AObject> operands) {
        this.operands = new ArrayList<>(operands);
    }

    public List<AObject> getOperands() {
        return operands;
    }

}
