package b.lang.exprs;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 17:30
 */
public abstract class AConst<T1 extends IExpr, T2 extends AConst<T1, T2>> extends ASymbol<T2> {

    private final T1 value;

    public AConst(String name, T1 value) {
        super(name);
        this.value = value;
    }

    public final T1 getValue() {
        return value;
    }

}
