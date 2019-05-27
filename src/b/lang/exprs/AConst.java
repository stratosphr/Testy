package b.lang.exprs;

import b.lang.ASymbol;
import b.lang.bobjectvisitors.IBObjectVisitor;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 17:30
 */
public abstract class AConst extends ASymbol {

    private final String name;
    private final IExpr value;

    public AConst(String name, IExpr value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public IExpr getValue() {
        return value;
    }

    @Override
    public final String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
