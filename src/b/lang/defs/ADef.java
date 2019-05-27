package b.lang.defs;

import b.lang.AObject;
import b.lang.ASymbol;
import b.lang.types.AType;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 01:19
 */
public abstract class ADef extends AObject {

    protected final String name;
    protected final AType type;

    public ADef(AType type, String name) {
        this.type = type;
        this.name = name;
    }

    public final AType getType() {
        return type;
    }

    public final String getName() {
        return name;
    }

    public abstract ASymbol getSymbol();

}
