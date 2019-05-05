package b.formatters;

import b.lang.exprs.Symbol;
import b.lang.exprs.arith.Number;
import b.lang.exprs.arith.*;

/**
 * Created by gvoiron on 05/05/19.
 * Time : 01:38
 */
public interface BObjectVisitor {

    String visit(Symbol symbol);

    String visit(Number number);

    String visit(ArithVar arithVar);

    String visit(Plus plus);

    String visit(Minus minus);

    String visit(Times times);

    String visit(Div div);

    String visit(Mod mod);

}
