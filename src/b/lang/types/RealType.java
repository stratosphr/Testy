package b.lang.types;

import b.bobjectvisitors.IBObjectVisitor;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 01:59
 */
public final class RealType extends AExprType {

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
