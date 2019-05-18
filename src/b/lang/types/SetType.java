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

    @Override
    public boolean instanceOf(AType type) {
        if (equals(type)) {
            return true;
        } else if (type instanceof SetType) {
            AType tmpElementsType1 = elementsType;
            AType tmpElementsType2 = ((SetType) type).getElementsType();
            while (tmpElementsType1 instanceof SetType && tmpElementsType2 instanceof SetType) {
                tmpElementsType1 = ((SetType) tmpElementsType1).getElementsType();
                tmpElementsType2 = ((SetType) tmpElementsType2).getElementsType();
            }
            if (tmpElementsType1.instanceOf(tmpElementsType2) || tmpElementsType2.instanceOf(tmpElementsType1)) {
                return true;
            } else {
                return tmpElementsType1.equals(tmpElementsType2);
            }
        }
        return false;
    }

}
