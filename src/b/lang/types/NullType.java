package b.lang.types;

import b.lang.bobjectvisitors.IBObjectVisitor;

/**
 * Created by gvoiron on 19/05/19.
 * Time : 14:19
 */
public final class NullType extends AType {

    private static NullType singleton;

    private NullType() {
    }

    static NullType getSingleton() {
        if (singleton == null) {
            singleton = new NullType();
        }
        return singleton;
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean instanceOf(AType type) {
        return false;
    }

}
