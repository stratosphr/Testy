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

import java.util.stream.Collectors;

/**
 * Created by gvoiron on 05/05/19.
 * Time : 01:39
 */
public final class BFormatter extends AFormatter implements IBObjectVisitor {

    @Override
    public String visit(ObjectType objectType) {
        return "object";
    }

    @Override
    public String visit(BoolType boolType) {
        return "bool";
    }

    @Override
    public String visit(ArithType arithType) {
        return "arith";
    }

    @Override
    public String visit(IntType intType) {
        return "int";
    }

    @Override
    public String visit(RealType realType) {
        return "real";
    }

    @Override
    public String visit(SetType setType) {
        return "set<" + setType.getElementsType().accept(this) + ">";
    }

    @Override
    public String visit(StringType stringType) {
        return "string";
    }

    @Override
    public String visit(ConstDef constDef) {
        return constDef.getSymbol().accept(this) + " " + constDef.getValue().accept(this);
    }

    @Override
    public String visit(SetDef setDef) {
        return null;
    }

    @Override
    public String visit(VarDef varDef) {
        return null;
    }

    @Override
    public String visit(FunDef funDef) {
        return null;
    }

    @Override
    public String visit(Number number) {
        return Double.toString(number.getValue());
    }

    @Override
    public String visit(ArithVar arithVar) {
        return arithVar.getName();
    }

    @Override
    public String visit(Plus plus) {
        return "(" + plus.getOperands().stream().map(operand -> operand.accept(this)).collect(Collectors.joining(" + ")) + ")";
    }

    @Override
    public String visit(Minus minus) {
        return "(" + minus.getOperands().stream().map(operand -> operand.accept(this)).collect(Collectors.joining(" - ")) + ")";
    }

    @Override
    public String visit(Times times) {
        return "(" + times.getOperands().stream().map(operand -> operand.accept(this)).collect(Collectors.joining(" * ")) + ")";
    }

    @Override
    public String visit(Div div) {
        return "(" + div.getOperands().stream().map(operand -> operand.accept(this)).collect(Collectors.joining(" / ")) + ")";
    }

    @Override
    public String visit(Mod mod) {
        return "(" + mod.getOperands().stream().map(operand -> operand.accept(this)).collect(Collectors.joining(" % ")) + ")";
    }

    @Override
    public String visit(Symbol symbol) {
        return symbol.getName();
    }

    @Override
    public String visit(False aFalse) {
        return "false";
    }

    @Override
    public String visit(True aTrue) {
        return "true";
    }

    @Override
    public String visit(Machine machine) {
        return null;
    }

}
