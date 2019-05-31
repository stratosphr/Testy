package b.lang.bobjectvisitors.primer;

import b.lang.exprs.FunCall;
import b.lang.exprs.FunSymbol;
import b.lang.exprs.arith.*;
import b.lang.exprs.bool.*;
import b.lang.exprs.set.Range;
import b.lang.exprs.set.Set;
import b.lang.exprs.set.SetConst;
import b.lang.exprs.set.SetVar;
import b.lang.exprs.string.StringConst;
import b.lang.exprs.string.StringVal;
import b.lang.exprs.string.StringVar;

/**
 * Created by gvoiron on 30/05/19.
 * Time : 02:16
 */
public interface IExprPrimer {

    FunCall visit(FunCall funCall);

    FunSymbol visit(FunSymbol funSymbol);

    Int visit(Int anInt);

    IntConst visit(IntConst intConst);

    IntVar visit(IntVar intVar);

    Real visit(Real real);

    RealConst visit(RealConst realConst);

    RealVar visit(RealVar realVar);

    Plus visit(Plus plus);

    Minus visit(Minus minus);

    Times visit(Times times);

    Div visit(Div div);

    Mod visit(Mod mod);

    BoolConst visit(BoolConst boolConst);

    BoolVar visit(BoolVar boolVar);

    Invariant visit(Invariant invariant);

    False visit(False aFalse);

    True visit(True aTrue);

    And visit(And and);

    Or visit(Or or);

    Eq visit(Eq eq);

    Range visit(Range range);

    Set visit(Set set);

    SetConst visit(SetConst setConst);

    SetVar visit(SetVar setVar);

    StringVal visit(StringVal stringVal);

    StringConst visit(StringConst stringConst);

    StringVar visit(StringVar stringVar);

}
