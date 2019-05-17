package b.lang.types;

import b.lang.bobjectvisitors.IBObjectVisitor;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 01:59
 */
public final class IntType extends AType {

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

}
