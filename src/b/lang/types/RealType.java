package b.lang.types;

import b.lang.bobjectvisitors.IBObjectVisitor;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 01:59
 */
public final class RealType extends AType {

    private static RealType singleton;

    private RealType() {
    }

    static RealType getSingleton() {
        if (singleton == null) {
            singleton = new RealType();
        }
        return singleton;
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
