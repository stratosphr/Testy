package b.lang.types;

import b.lang.bobjectvisitors.IBObjectVisitor;

import static b.lang.types.Types.*;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 01:59
 */
public final class IntType extends ArithType {

    private static IntType singleton;

    private IntType() {
    }

    static IntType getSingleton() {
        if (singleton == null) {
            singleton = new IntType();
        }
        return singleton;
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean instanceOf(AType type) {
        return type != null && (equals(type) || type.equals(getObjectType()) || type.equals(getRealType()) || type.equals(getArithType()));
    }

}
