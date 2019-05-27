package b.lang.bobjectvisitors;

import b.lang.Event;
import b.lang.Machine;
import b.lang.defs.ConstDef;
import b.lang.defs.FunDef;
import b.lang.defs.SetDef;
import b.lang.defs.VarDef;
import b.lang.exprs.AConst;
import b.lang.exprs.ASymbol;
import b.lang.exprs.arith.*;
import b.lang.exprs.bool.Eq;
import b.lang.exprs.bool.False;
import b.lang.exprs.bool.True;
import b.lang.exprs.set.Range;
import b.lang.exprs.set.Set;
import b.lang.exprs.string.StringVal;
import b.lang.substitutions.*;
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
    public String visit(NullType nullType) {
        return "null";
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
        return constDef.getType().accept(this) + " " + constDef.getName() + " = " + constDef.getValue().accept(this);
    }

    @Override
    public String visit(SetDef setDef) {
        return setDef.getType().accept(this) + " " + setDef.getName() + " = " + setDef.getValue().accept(this);
    }

    @Override
    public String visit(VarDef varDef) {
        return varDef.getType().accept(this) + " " + varDef.getName() + " : " + varDef.getDomain();
    }

    @Override
    public String visit(FunDef funDef) {
        return funDef.getType().accept(this) + " -> " + funDef.getCoType().accept(this) + " " + funDef.getName() + " : " + funDef.getDomain().accept(this) + " -> " + funDef.getCoDomain().accept(this);
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
    public String visit(False aFalse) {
        return "false";
    }

    @Override
    public String visit(True aTrue) {
        return "true";
    }

    @Override
    public String visit(Machine machine) {
        String toString = line("MACHINE " + machine.getName());
        toString += line("");
        toString += indentRight() + indentLine("CONST_DEFS");
        toString += indentRight() + machine.getConstDefs().stream().map(constDef -> indentLine(constDef.accept(this))).collect(Collectors.joining());
        indentLeft();
        indentLeft();
        toString += line("");
        toString += indentRight() + indentLine("SET_DEFS");
        toString += indentRight() + machine.getSetDefs().stream().map(constDef -> indentLine(constDef.accept(this))).collect(Collectors.joining());
        indentLeft();
        indentLeft();
        toString += line("");
        toString += indentRight() + indentLine("VAR_DEFS");
        toString += indentRight() + machine.getVarDefs().stream().map(constDef -> indentLine(constDef.accept(this))).collect(Collectors.joining());
        indentLeft();
        indentLeft();
        toString += line("");
        toString += indentRight() + indentLine("FUN_DEFS");
        toString += indentRight() + machine.getFunDefs().stream().map(constDef -> indentLine(constDef.accept(this))).collect(Collectors.joining());
        indentLeft();
        indentLeft();
        toString += line("");
        toString += indentRight() + indentLine("INITIALISATION");
        toString += indentRight() + indentLine(machine.getInitialisation().accept(this));
        indentLeft();
        indentLeft();
        toString += line("");
        toString += indentRight() + indentLine("EVENTS");
        toString += line("");
        toString += indentRight() + machine.getEvents().stream().map(event -> indentLine(event.accept(this))).collect(Collectors.joining(line("")));
        return toString;
    }

    @Override
    public String visit(Int anInt) {
        return anInt.getValue().toString();
    }

    @Override
    public String visit(Real real) {
        return real.getValue().toString();
    }

    @Override
    public String visit(Range range) {
        return "[" + range.getLowerBound() + ".." + range.getUpperBound() + "]";
    }

    @Override
    public String visit(AConst aConst) {
        return aConst.getName();
    }

    @Override
    public String visit(Set set) {
        return "{" + set.getElements().stream().map(Object::toString).collect(Collectors.joining(", ")) + "}";
    }

    @Override
    public String visit(StringVal stringVal) {
        return stringVal.getValue();
    }

    @Override
    public String visit(Sequence sequence) {
        return sequence.getSubstitutions().stream().map(substitution -> substitution.accept(this)).collect(Collectors.joining(line(";") + indent("")));
    }

    @Override
    public String visit(VarAssignment varAssignment) {
        return varAssignment.getVar().accept(this) + " := " + varAssignment.getExpr().accept(this);
    }

    @Override
    public String visit(ASymbol symbol) {
        return symbol.getName();
    }

    @Override
    public String visit(FunAssignment funAssignment) {
        return funAssignment.getFun().accept(this) + "(" + funAssignment.getParameter().accept(this) + ") := " + funAssignment.getValue().accept(this);
    }

    @Override
    public String visit(Event event) {
        return line(event.getName() + " =") + indentRight() + indent(event.getSubstitution().accept(this)) + indentLeft();
    }

    @Override
    public String visit(Skip skip) {
        return "SKIP";
    }

    @Override
    public String visit(Select select) {
        return line("SELECT") + indentRight() + indentLine(select.getCondition().accept(this)) + indentLeft() + indentLine("THEN") + indentRight() + indentLine(select.getSubstitution().accept(this)) + indentLeft() + indent("END");
    }

    @Override
    public String visit(Eq eq) {
        return eq.getLeft().accept(this) + " = " + eq.getRight().accept(this);
    }

}
