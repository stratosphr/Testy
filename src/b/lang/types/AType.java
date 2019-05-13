package b.lang.types;

import b.lang.AObject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 02:00
 */
public abstract class AType extends AObject {

    public static List<AType> getExprTypes() {
        return Arrays.asList(new BoolType(), new IntType(), new RealType(), new SetType());
    }

}
