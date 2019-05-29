package b.lang.exprs.bool;

import b.lang.bobjectvisitors.IBObjectVisitor;

import java.util.Arrays;
import java.util.LinkedHashSet;

/**
 * Created by gvoiron on 30/05/19.
 * Time : 00:44
 */
public class And extends ANaryBoolExpr {

    public And(IBoolExpr... operands) {
        super(new LinkedHashSet<>(Arrays.asList(operands)));
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
