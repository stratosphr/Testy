package b.lang.types;

/**
 * Created by gvoiron on 17/05/19.
 * Time : 12:41
 */
public final class Types {

    public static BoolType getBoolType() {
        return BoolType.getSingleton();
    }

    public static IntType getIntType() {
        return IntType.getSingleton();
    }

    public static RealType getRealType() {
        return RealType.getSingleton();
    }

    public static StringType getStringType() {
        return StringType.getSingleton();
    }

    public static SetType getSetType(AType type) {
        return new SetType(type);
    }

}
