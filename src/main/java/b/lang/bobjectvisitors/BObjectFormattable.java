package b.lang.bobjectvisitors;

/**
 * Created by gvoiron on 05/05/19.
 * Time : 01:38
 */
public interface BObjectFormattable {

    String accept(IBObjectVisitor visitor);

}
