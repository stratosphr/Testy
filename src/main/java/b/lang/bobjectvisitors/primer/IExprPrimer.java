package b.lang.bobjectvisitors.primer;

import b.lang.exprs.FunCall;
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

    Plus visit(Plus plus);

    ANumber<Integer> visit(Int anInt);

    Minus visit(Minus minus);

    Times visit(Times times);

    Div visit(Div div);

    Mod visit(Mod mod);

    False visit(False aFalse);

    True visit(True aTrue);

    ANumber<Double> visit(Real real);

    Range visit(Range range);

    Set visit(Set set);

    StringVal visit(StringVal stringVal);

    Eq visit(Eq eq);

    Invariant visit(Invariant invariant);

    And visit(And and);

    Or visit(Or or);

    RealConst visit(RealConst realConst);

    SetConst visit(SetConst setConst);

    BoolVar visit(BoolVar boolVar);

    IntConst visit(IntConst intConst);

    StringConst visit(StringConst stringConst);

    SetVar visit(SetVar setVar);

    IntVar visit(IntVar intVar);

    RealVar visit(RealVar realVar);

    StringVar visit(StringVar stringVar);

    BoolConst visit(BoolConst boolConst);

    FunCall visit(FunCall funCall);

}
