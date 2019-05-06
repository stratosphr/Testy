package b.parser.astmodifiers;

import b.parser.*;

import java.util.Map;

/**
 * Created by gvoiron on 06/05/19.
 * Time : 20:25
 */
public final class ASTSimplifier implements BParserVisitor {

    @Override
    public Object visit(SimpleNode node, Map<Object, Object> data) {
        node.jjtAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTMachine node, Map<Object, Object> data) {
        return null;
    }

    @Override
    public Object visit(ASTExpr node, Map<Object, Object> data) {
        return null;
    }

    @Override
    public Object visit(ASTPlus node, Map<Object, Object> data) {
        return null;
    }

    @Override
    public Object visit(ASTMinus node, Map<Object, Object> data) {
        return null;
    }

    @Override
    public Object visit(ASTTimes node, Map<Object, Object> data) {
        return null;
    }

    @Override
    public Object visit(ASTDiv node, Map<Object, Object> data) {
        return null;
    }

    @Override
    public Object visit(ASTMod node, Map<Object, Object> data) {
        return null;
    }

    @Override
    public Object visit(ASTIdentifier node, Map<Object, Object> data) {
        return null;
    }

    @Override
    public Object visit(ASTNumber node, Map<Object, Object> data) {
        return null;
    }

}
