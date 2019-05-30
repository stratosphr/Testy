package b.lang.types;

import b.lang.bobjectvisitors.formatter.IBFormatter;

import static b.lang.types.Types.getObjectType;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 01:59
 */
public final class BoolType extends AType {

    private static BoolType singleton;

    private BoolType() {
    }

    static BoolType getSingleton() {
        if (singleton == null) {
            singleton = new BoolType();
        }
        return singleton;
    }

    @Override
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean instanceOf(AType type) {
        return type != null && (equals(type) || type.equals(getObjectType()));
    }

}
