package b.lang.exprs;

/**
 * Created by gvoiron on 02/06/19.
 * Time : 23:27
 */
public abstract class AAssignable<T extends IExpr<T>> extends ASymbol<T> {

    public AAssignable(String name) {
        super(name);
    }

}
