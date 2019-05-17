package b.lang.types;

import b.lang.bobjectvisitors.IBObjectVisitor;

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
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
