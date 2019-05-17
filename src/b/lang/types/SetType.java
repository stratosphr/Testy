package b.lang.types;

import b.lang.bobjectvisitors.IBObjectVisitor;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 01:59
 */
public final class SetType extends AType {

    private final AType elementsType;

    public SetType(AType elementsType) {
        this.elementsType = elementsType;
    }

    public AType getElementsType() {
        return elementsType;
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
