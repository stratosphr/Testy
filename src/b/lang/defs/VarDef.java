package b.lang.defs;

import b.lang.bobjectvisitors.IBObjectVisitor;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 01:31
 */
public final class VarDef extends ADef {

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
