package b.formatters;

import b.lang.exprs.Symbol;
import b.lang.exprs.arith.Number;
import b.lang.exprs.arith.*;

import java.util.stream.Collectors;

/**
 * Created by gvoiron on 05/05/19.
 * Time : 01:39
 */
public final class BFormatter extends AFormatter implements BObjectVisitor {

    @Override
    public String visit(Symbol symbol) {
        return symbol.getName();
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

}
