package b.lang.bobjectvisitors.formatter;

/**
 * Created by gvoiron on 05/05/19.
 * Time : 01:38
 */
public interface IBFormattable {

    String accept(IBFormatter visitor);

}
