package b.lang.types;

import b.lang.bobjectvisitors.formatter.IBFormatter;

import static b.lang.types.Types.*;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 01:59
 */
public class ArithType extends AType {

    private static ArithType singleton;

    protected ArithType() {
    }

    static ArithType getSingleton() {
        if (singleton == null) {
            singleton = new ArithType();
        }
        return singleton;
    }

    @Override
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean instanceOf(AType type) {
        return type != null && (equals(type) || type.equals(getObjectType()) || type.equals(getIntType()) || type.equals(getRealType()));
    }

}
