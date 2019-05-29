package tools;

/**
 * Created by gvoiron on 29/05/19.
 * Time : 18:12
 */
public enum EDictionary {

    SYMBOL("Symbol");

    private String toString;

    EDictionary(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }

}
