package b.parser;

/**
 * Created by gvoiron on 04/05/19.
 * Time : 22:35
 */
public class ASTVisitor implements BParserVisitor {

    @Override
    public Object visit(SimpleNode node, Object data) {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTMachine node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTExpr node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTPlus node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTMinus node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTTimes node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTDiv node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTMod node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTIdentifier node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTNumber node, Object data) {
        return null;
    }

}
