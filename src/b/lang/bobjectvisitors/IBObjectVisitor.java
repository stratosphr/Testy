package b.lang.bobjectvisitors;

import b.lang.Machine;
import b.lang.Symbol;
import b.lang.defs.ConstDef;
import b.lang.defs.FunDef;
import b.lang.defs.SetDef;
import b.lang.defs.VarDef;
import b.lang.exprs.arith.Number;
import b.lang.exprs.arith.*;
import b.lang.exprs.bool.False;
import b.lang.exprs.bool.True;
import b.lang.types.*;

/**
 * Created by gvoiron on 05/05/19.
 * Time : 01:38
 */
public interface IBObjectVisitor {

    String visit(ConstDef constDef);

    String visit(BoolType boolType);

    String visit(IntType intType);

    String visit(RealType realType);

    String visit(SetType setType);

    String visit(StringType stringType);

    String visit(SetDef setDef);

    String visit(VarDef varDef);

    String visit(FunDef funDef);

    String visit(Number number);

    String visit(ArithVar arithVar);

    String visit(Plus plus);

    String visit(Minus minus);

    String visit(Times times);

    String visit(Div div);

    String visit(Mod mod);

    String visit(Symbol symbol);

    String visit(False aFalse);

    String visit(True aTrue);

    String visit(Machine machine);

}
