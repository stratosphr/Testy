package b.parser;

import b.lang.exprs.arith.Number;
import b.lang.exprs.arith.*;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by gvoiron on 04/05/19.
 * Time : 22:35
 */
public final class ASTAnalyzer extends ATypeChecker implements BParserVisitor {

    @Override
    public Object visit(SimpleNode node, Map<Object, Object> data) {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTMachine node, Map<Object, Object> data) {
        return null;
    }

    @Override
    public Object visit(ASTExpr node, Map<Object, Object> data) {
        return node.jjtGetChild(0).jjtAccept(this, data);
    }

    @Override
    public Object visit(ASTPlus node, Map<Object, Object> data) {
        return new Plus(Arrays.stream(node.children).map(child -> checkArith(child.jjtAccept(this, data), (SimpleNode) child, data)).toArray(AArithExpr[]::new));
    }

    @Override
    public Object visit(ASTMinus node, Map<Object, Object> data) {
        return new Minus(Arrays.stream(node.children).map(child -> checkArith(child.jjtAccept(this, data), (SimpleNode) child, data)).toArray(AArithExpr[]::new));
    }

    @Override
    public Object visit(ASTTimes node, Map<Object, Object> data) {
        return new Times(Arrays.stream(node.children).map(child -> checkArith(child.jjtAccept(this, data), (SimpleNode) child, data)).toArray(AArithExpr[]::new));
    }

    @Override
    public Object visit(ASTDiv node, Map<Object, Object> data) {
        return new Div(Arrays.stream(node.children).map(child -> checkArith(child.jjtAccept(this, data), (SimpleNode) child, data)).toArray(AArithExpr[]::new));
    }

    @Override
    public Object visit(ASTMod node, Map<Object, Object> data) {
        return new Mod(Arrays.stream(node.children).map(child -> checkArith(child.jjtAccept(this, data), (SimpleNode) child, data)).toArray(AArithExpr[]::new));
    }

    @Override
    public Object visit(ASTIdentifier node, Map<Object, Object> data) {
        return getSymbolOrRegister(node.jjtGetValue().toString());
    }

    @Override
    public Object visit(ASTNumber node, Map<Object, Object> data) {
        return new Number(Double.parseDouble(node.jjtGetValue().toString()));
    }

}
