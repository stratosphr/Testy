package b.lang.types;

import b.lang.bobjectvisitors.formatter.IBFormatter;

import static b.lang.types.Types.getObjectType;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 14:38
 */
public final class StringType extends AType {

    private static StringType singleton;

    private StringType() {
    }

    static StringType getSingleton() {
        if (singleton == null) {
            singleton = new StringType();
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
