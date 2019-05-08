package b.parser.astvisitors;

import b.parser.*;

import java.util.*;

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
        private final List<Class<? extends SimpleNode>> unaryOperators;

        public NestedASTSimplifier() {
            this.op1 = new Stack<>();
            this.op2 = new LinkedHashMap<>();
            this.simplifiedNodes = new LinkedHashMap<>();
            this.unaryOperators = Arrays.asList(ASTNot.class, ASTUMinus.class, ASTSet.class);
        }

        private SimpleNode simplifyOperator(SimpleNode node, SimpleNode newNode, Map<Object, Object> data) {
            if (unaryOperators.contains(node.getClass()) || op1.isEmpty() || op1.peek().getClass() != node.getClass()) {
                op1.push(node);
                op2.put(simplifiedNodes.size(), 0);
                newNode.setSourceCoordinates(node.getSourceCoordinates());
                newNode.setValue(node.jjtGetValue());
                simplifiedNodes.put(simplifiedNodes.size(), newNode);
            }
            op2.put(simplifiedNodes.size() - 1, op2.get(simplifiedNodes.size() - 1) + 1);
            Arrays.stream(node.getChildren()).forEach(child -> child.jjtAccept(this, data));
            return simplifiedNodes.get(op1.size() - 1);
        }

        private SimpleNode simplifyTerminal(SimpleNode node, SimpleNode newNode) {
            newNode.setSourceCoordinates(node.getSourceCoordinates());
            newNode.setValue(node.jjtGetValue());
            if (op1.size() >= 1) {
                simplifiedNodes.get(op1.size() - 1).jjtAddChild(newNode);
                if (unaryOperators.contains(simplifiedNodes.get(op1.size() - 1).getClass()) || simplifiedNodes.get(op1.size() - 1).jjtGetNumChildren() > 1) {
                    op2.put(op1.size() - 1, op2.get(op1.size() - 1) - 1);
                }
                while (op1.size() > 1 && op2.get(op1.size() - 1) == 0) {
                    op1.pop();
                    simplifiedNodes.get(op1.size() - 1).jjtAddChild(simplifiedNodes.get(op1.size()));
                    if (unaryOperators.contains(simplifiedNodes.get(op1.size() - 1).getClass()) || simplifiedNodes.get(op1.size() - 1) instanceof ASTNot || simplifiedNodes.get(op1.size() - 1) instanceof ASTSet || simplifiedNodes.get(op1.size() - 1).jjtGetNumChildren() > 1) {
                        op2.put(op1.size() - 1, op2.get(op1.size() - 1) - 1);
                    }
                    simplifiedNodes.remove(op1.size());
                }
            }
            return newNode;
        }

        @Override
        public Object visit(SimpleNode node, Map<Object, Object> data) {
            throw new Error("Unable to simplify abstract node \"" + node + "\".");
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
        public Object visit(ASTNot node, Map<Object, Object> data) {
            return simplifyOperator(node, new ASTNot(node.getId()), data);
        }

        @Override
        public Object visit(ASTEquiv node, Map<Object, Object> data) {
            return simplifyOperator(node, new ASTEquiv(node.getId()), data);
        }

        @Override
        public Object visit(ASTImplies node, Map<Object, Object> data) {
            return simplifyOperator(node, new ASTImplies(node.getId()), data);
        }

        @Override
        public Object visit(ASTOr node, Map<Object, Object> data) {
            return simplifyOperator(node, new ASTOr(node.getId()), data);
        }

        @Override
        public Object visit(ASTAnd node, Map<Object, Object> data) {
            return simplifyOperator(node, new ASTAnd(node.getId()), data);
        }

        @Override
        public Object visit(ASTEq node, Map<Object, Object> data) {
            return simplifyOperator(node, new ASTEq(node.getId()), data);
        }

        @Override
        public Object visit(ASTNEq node, Map<Object, Object> data) {
            return simplifyOperator(node, new ASTNEq(node.getId()), data);
        }

        @Override
        public Object visit(ASTLT node, Map<Object, Object> data) {
            return simplifyOperator(node, new ASTLT(node.getId()), data);
        }

        @Override
        public Object visit(ASTLE node, Map<Object, Object> data) {
            return simplifyOperator(node, new ASTLE(node.getId()), data);
        }

        @Override
        public Object visit(ASTGT node, Map<Object, Object> data) {
            return simplifyOperator(node, new ASTGT(node.getId()), data);
        }

        @Override
        public Object visit(ASTGE node, Map<Object, Object> data) {
            return simplifyOperator(node, new ASTGE(node.getId()), data);
        }

        @Override
        public Object visit(ASTIn node, Map<Object, Object> data) {
            return simplifyOperator(node, new ASTIn(node.getId()), data);
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
            return simplifyTerminal(node, new ASTIdentifier(node.getId()));
        }

        @Override
        public Object visit(ASTFalse node, Map<Object, Object> data) {
            return simplifyTerminal(node, new ASTFalse(node.getId()));
        }

        @Override
        public Object visit(ASTTrue node, Map<Object, Object> data) {
            return simplifyTerminal(node, new ASTTrue(node.getId()));
        }

        @Override
        public Object visit(ASTDouble node, Map<Object, Object> data) {
            return simplifyTerminal(node, new ASTDouble(node.getId()));
        }

        @Override
        public Object visit(ASTInt node, Map<Object, Object> data) {
            return simplifyTerminal(node, new ASTInt(node.getId()));
        }

        @Override
        public Object visit(ASTEmptySet node, Map<Object, Object> data) {
            return simplifyTerminal(node, new ASTEmptySet(node.getId()));
        }

        @Override
        public Object visit(ASTSet node, Map<Object, Object> data) {
            return simplifyOperator(node, new ASTSet(node.getId()), data);
        }

        @Override
        public Object visit(ASTSeq node, Map<Object, Object> data) {
            return simplifyOperator(node, new ASTSeq(node.getId()), data);
        }

    }

}
