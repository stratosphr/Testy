package b.lang.types;

import b.bobjectvisitors.IBObjectVisitor;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 01:59
 */
public final class SetType<T extends AType> extends AType {

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}