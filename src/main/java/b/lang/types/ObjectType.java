package b.lang.types;

import b.lang.bobjectvisitors.IBObjectVisitor;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 01:59
 */
public class ObjectType extends AType {

    private static ObjectType singleton;

    protected ObjectType() {
    }

    static ObjectType getSingleton() {
        if (singleton == null) {
            singleton = new ObjectType();
        }
        return singleton;
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean instanceOf(AType type) {
        return type != null;
    }

}
