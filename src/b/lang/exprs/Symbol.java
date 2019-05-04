package b.lang.exprs;

/**
 * Created by gvoiron on 04/05/19.
 * Time : 23:55
 */
public final class Symbol extends AExpr {

    private String name;

    public Symbol(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
