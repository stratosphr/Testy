package b.bobjectvisitors;

import b.lang.Symbol;
import b.lang.defs.ConstDef;
import b.lang.defs.FunDef;
import b.lang.defs.SetDef;
import b.lang.defs.VarDef;
import b.lang.exprs.arith.Number;
import b.lang.exprs.arith.*;
import b.lang.exprs.bool.False;
import b.lang.types.AType;
import b.lang.types.BoolType;
import b.lang.types.SetType;

/**
 * Created by gvoiron on 05/05/19.
 * Time : 01:38
 */
public interface IBObjectVisitor {

    String visit(ConstDef constDef);

    String visit(BoolType boolType);

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

    <T extends AType> String visit(SetType<T> tSetType);

    String visit(Symbol symbol);

    String visit(False aFalse);

}
