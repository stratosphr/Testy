package b.lang.bobjectvisitors.formatter;

import b.lang.Event;
import b.lang.Machine;
import b.lang.defs.ConstDef;
import b.lang.defs.FunDef;
import b.lang.defs.SetDef;
import b.lang.defs.VarDef;
import b.lang.exprs.AConst;
import b.lang.exprs.FunCall;
import b.lang.exprs.FunSymbol;
import b.lang.exprs.arith.*;
import b.lang.exprs.bool.*;
import b.lang.exprs.set.Range;
import b.lang.exprs.set.Set;
import b.lang.exprs.set.SetVar;
import b.lang.exprs.string.StringVal;
import b.lang.exprs.string.StringVar;
import b.lang.substitutions.*;
import b.lang.types.*;

/**
 * Created by gvoiron on 05/05/19.
 * Time : 01:38
 */
public interface IBFormatter {

    String visit(ConstDef constDef);

    String visit(ObjectType objectType);

    String visit(NullType nullType);

    String visit(BoolType boolType);

    String visit(ArithType arithType);

    String visit(IntType intType);

    String visit(RealType realType);

    String visit(SetType setType);

    String visit(StringType stringType);

    String visit(SetDef setDef);

    String visit(VarDef varDef);

    String visit(FunDef funDef);

    String visit(Plus plus);

    String visit(Minus minus);

    String visit(Times times);

    String visit(Div div);

    String visit(Mod mod);

    String visit(False aFalse);

    String visit(True aTrue);

    String visit(Machine machine);

    String visit(Int anInt);

    String visit(Real real);

    String visit(Range range);

    String visit(AConst aConst);

    String visit(Set set);

    String visit(StringVal stringVal);

    String visit(Skip skip);

    String visit(Sequence sequence);

    String visit(VarAssignment varAssignment);

    String visit(FunAssignment funAssignment);

    String visit(Event event);

    String visit(Eq eq);

    String visit(Select select);

    String visit(Invariant invariant);

    String visit(And and);

    String visit(Or or);

    String visit(BoolVar boolVar);

    String visit(FunCall funCall);

    String visit(FunSymbol funSymbol);

    String visit(IntVar intVar);

    String visit(RealVar realVar);

    String visit(SetVar setVar);

    String visit(StringVar stringVar);

}
