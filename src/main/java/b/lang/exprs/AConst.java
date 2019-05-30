package b.lang.exprs;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 17:30
 */
public abstract class AConst<T extends AConst<T>> extends ASymbol<T> {

    private final IExpr value;

    public AConst(String name, IExpr value) {
        super(name);
        this.value = value;
    }

    public final IExpr getValue() {
        return value;
    }

}
