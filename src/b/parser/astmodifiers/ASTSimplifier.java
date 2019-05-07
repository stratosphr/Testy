package b.parser.astmodifiers;

import b.parser.*;

import java.util.Arrays;
import java.util.LinkedHashMap;
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

        private final Stack<SimpleNode> op1;
        private final Map<Integer, Integer> op2;
        private final Map<Integer, SimpleNode> simplifiedNodes;

        public NestedASTSimplifier() {
            this.op1 = new Stack<>();
            this.op2 = new LinkedHashMap<>();
            this.simplifiedNodes = new LinkedHashMap<>();
        }

        private SimpleNode simplifyOperator(SimpleNode node, SimpleNode newNode, Map<Object, Object> data) {
            if (op1.isEmpty() || op1.peek().getClass() != node.getClass()) {
                op1.push(node);
                op2.put(simplifiedNodes.size(), 0);
                newNode.setSourceCoordinates(node.getSourceCoordinates());
                newNode.setValue(node.jjtGetValue());
                simplifiedNodes.put(simplifiedNodes.size(), newNode);
            }
            op2.put(simplifiedNodes.size() - 1, op2.get(simplifiedNodes.size() - 1) + 1);
            Arrays.stream(node.getChildren()).forEach(child -> child.jjtAccept(this, data));
            /*node.getChildren()[0].jjtAccept(this, data);
            node.getChildren()[1].jjtAccept(this, data);*/
            return simplifiedNodes.get(op1.size() - 1);
        }

        private SimpleNode simplifyTerminal(SimpleNode node, SimpleNode newNode, Map<Object, Object> data) {
            newNode.setSourceCoordinates(node.getSourceCoordinates());
            newNode.setValue(node.jjtGetValue());
            if (op1.size() >= 1) {
                simplifiedNodes.get(op1.size() - 1).jjtAddChild(newNode);
                if (simplifiedNodes.get(op1.size() - 1) instanceof ASTUMinus || simplifiedNodes.get(op1.size() - 1).jjtGetNumChildren() > 1) {
                    op2.put(op1.size() - 1, op2.get(op1.size() - 1) - 1);
                }
                while (op1.size() > 1 && op2.get(op1.size() - 1) == 0) {
                    op1.pop();
                    simplifiedNodes.get(op1.size() - 1).jjtAddChild(simplifiedNodes.get(op1.size()));
                    if (simplifiedNodes.get(op1.size() - 1) instanceof ASTUMinus || simplifiedNodes.get(op1.size() - 1).jjtGetNumChildren() > 1) {
                        op2.put(op1.size() - 1, op2.get(op1.size() - 1) - 1);
                    }
                    simplifiedNodes.remove(op1.size());
                }
            }
            return newNode;
        }

        @Override
        public Object visit(SimpleNode node, Map<Object, Object> data) {
            return node.jjtAccept(this, data);
        }

        @Override
        public Object visit(ASTMachine node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTExpr node, Map<Object, Object> data) {
            return node.getChildren()[0].jjtAccept(this, data);
        }

        @Override
        public Object visit(ASTPlus node, Map<Object, Object> data) {
            return simplifyOperator(node, new ASTPlus(node.getId()), data);
        }

        @Override
        public Object visit(ASTMinus node, Map<Object, Object> data) {
            return simplifyOperator(node, new ASTMinus(node.getId()), data);
        }

        @Override
        public Object visit(ASTTimes node, Map<Object, Object> data) {
            return simplifyOperator(node, new ASTTimes(node.getId()), data);
        }

        @Override
        public Object visit(ASTDiv node, Map<Object, Object> data) {
            return simplifyOperator(node, new ASTDiv(node.getId()), data);
        }

        @Override
        public Object visit(ASTMod node, Map<Object, Object> data) {
            return simplifyOperator(node, new ASTMod(node.getId()), data);
        }

        @Override
        public Object visit(ASTUMinus node, Map<Object, Object> data) {
            return simplifyOperator(node, new ASTUMinus(node.getId()), data);
        }

        @Override
        public Object visit(ASTIdentifier node, Map<Object, Object> data) {
            return simplifyTerminal(node, new ASTIdentifier(node.getId()), data);
        }

        @Override
        public Object visit(ASTNumber node, Map<Object, Object> data) {
            return simplifyTerminal(node, new ASTNumber(node.getId()), data);
        }

    }

}
