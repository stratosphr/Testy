package b.lang.exprs;

import b.lang.AObject;
import b.lang.bobjectvisitors.formatter.IBFormatter;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 17:17
 */
public abstract class ASymbol<T extends IExpr<T>> extends AObject implements IExpr<T> {

    private final String name;

    public ASymbol(String name) {
        this.name = name;
    }

    public final String getName() {
        return name;
    }

    @Override
    public final String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

}
