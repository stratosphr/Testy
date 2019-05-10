package b.parser.astvisitors;

import b.parser.*;

import java.util.Map;
import java.util.Stack;

/**
 * Created by gvoiron on 06/05/19.
 * Time : 20:25
 */
public final class ASTSimplifier {

    public SimpleNode simplify(Node node) {
        return (SimpleNode) node.jjtAccept(new NestedASTSimplifier(), null);
    }

    private final class NestedASTSimplifier implements BParserVisitor {

        private SimpleNode simplifyChildren(SimpleNode node) {
            for (int i = 0; i < node.getChildren().length; i++) {
                node.jjtAddChild((Node) node.jjtGetChild(i).jjtAccept(this, null), i);
            }
            return node;
        }

        private Object simplifyUnaryOperator(SimpleNode node) {
            node.jjtAddChild((Node) node.jjtGetChild(0).jjtAccept(this, null), 0);
            return node;
        }

        private SimpleNode simplifyBinaryOperator(SimpleNode node) {
            Stack<SimpleNode> operands = new Stack<>();
            SimpleNode left = node;
            while (left.getClass().equals(node.getClass())) {
                operands.push((SimpleNode) left.jjtGetChild(1).jjtAccept(this, null));
                left = (SimpleNode) left.jjtGetChild(0);
            }
            operands.add((SimpleNode) left.jjtAccept(this, null));
            int i = 0;
            while (!operands.isEmpty()) {
                SimpleNode operand = operands.pop();
                if (operand.getClass().equals(node.getClass())) {
                    for (Node child : operand.getChildren()) {
                        node.jjtAddChild(child, i++);
                    }
                } else {
                    node.jjtAddChild(operand, i++);
                }
            }
            return node;
        }

        @Override
        public Object visit(SimpleNode node, Map<Object, Object> data) {
            throw new Error("Unable to simplify abstract node \"" + node + "\".");
        }

        @Override
        public Object visit(ASTMachine node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTConstDefs node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTConstDef node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTSetDefs node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTSetDef node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTVarDefs node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTFunDefs node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTInvariant node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTSubstitution node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTEvents node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTEvent node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTSkip node, Map<Object, Object> data) {
            return node;
        }

        @Override
        public Object visit(ASTVarAssignment node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTFunAssignment node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTSelect node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTIfThenElse node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTChoice node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTQuantifiedSymbolsDefs node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTAny node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTSequence node, Map<Object, Object> data) {
            return simplifyBinaryOperator(node);
        }

        @Override
        public Object visit(ASTDef node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTFunDef node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTVarDef node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTExpr node, Map<Object, Object> data) {
            return node.jjtGetChild(0).jjtAccept(this, data);
        }

        @Override
        public Object visit(ASTEquiv node, Map<Object, Object> data) {
            return simplifyBinaryOperator(node);
        }

        @Override
        public Object visit(ASTImplies node, Map<Object, Object> data) {
            return simplifyBinaryOperator(node);
        }

        @Override
        public Object visit(ASTOr node, Map<Object, Object> data) {
            return simplifyBinaryOperator(node);
        }

        @Override
        public Object visit(ASTAnd node, Map<Object, Object> data) {
            return simplifyBinaryOperator(node);
        }

        @Override
        public Object visit(ASTEq node, Map<Object, Object> data) {
            return simplifyBinaryOperator(node);
        }

        @Override
        public Object visit(ASTNEq node, Map<Object, Object> data) {
            return simplifyBinaryOperator(node);
        }

        @Override
        public Object visit(ASTIn node, Map<Object, Object> data) {
            return simplifyBinaryOperator(node);
        }

        @Override
        public Object visit(ASTNotIn node, Map<Object, Object> data) {
            return simplifyBinaryOperator(node);
        }

        @Override
        public Object visit(ASTExists node, Map<Object, Object> data) {
            return simplifyBinaryOperator(node);
        }

        @Override
        public Object visit(ASTForAll node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTLT node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTLE node, Map<Object, Object> data) {
            return simplifyBinaryOperator(node);
        }

        @Override
        public Object visit(ASTGT node, Map<Object, Object> data) {
            return simplifyBinaryOperator(node);
        }

        @Override
        public Object visit(ASTGE node, Map<Object, Object> data) {
            return simplifyBinaryOperator(node);
        }

        @Override
        public Object visit(ASTPlus node, Map<Object, Object> data) {
            return simplifyBinaryOperator(node);
        }

        @Override
        public Object visit(ASTMinus node, Map<Object, Object> data) {
            return simplifyBinaryOperator(node);
        }

        @Override
        public Object visit(ASTTimes node, Map<Object, Object> data) {
            return simplifyBinaryOperator(node);
        }

        @Override
        public Object visit(ASTDiv node, Map<Object, Object> data) {
            return simplifyBinaryOperator(node);
        }

        @Override
        public Object visit(ASTMod node, Map<Object, Object> data) {
            return simplifyBinaryOperator(node);
        }

        @Override
        public Object visit(ASTNot node, Map<Object, Object> data) {
            return simplifyUnaryOperator(node);
        }

        @Override
        public Object visit(ASTUMinus node, Map<Object, Object> data) {
            return simplifyUnaryOperator(node);
        }

        @Override
        public Object visit(ASTFunCall node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTFalse node, Map<Object, Object> data) {
            return node;
        }

        @Override
        public Object visit(ASTTrue node, Map<Object, Object> data) {
            return node;
        }

        @Override
        public Object visit(ASTDouble node, Map<Object, Object> data) {
            return node;
        }

        @Override
        public Object visit(ASTInt node, Map<Object, Object> data) {
            return node;
        }

        @Override
        public Object visit(ASTIdentifier node, Map<Object, Object> data) {
            return node;
        }

        @Override
        public Object visit(ASTSet node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

        @Override
        public Object visit(ASTRange node, Map<Object, Object> data) {
            return simplifyChildren(node);
        }

    }

}
