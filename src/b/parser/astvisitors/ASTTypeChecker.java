package b.parser.astvisitors;

import b.lang.types.AType;
import b.lang.types.RealType;
import b.lang.types.SetType;
import b.parser.*;

import java.util.*;
import java.util.stream.Collectors;

import static b.lang.types.Types.*;

/**
 * Created by gvoiron on 13/05/19.
 * Time : 14:49
 */
// TODO: Do not throw errors directly but store errors messages and throw a single error at the end of function "ASTTypeChecker::checkTypes(ASTMachine machine)"
// TODO: Type checker should also check if a constant is assigned
public final class ASTTypeChecker {

    private final List<String> errors;

    public ASTTypeChecker() {
        this.errors = new ArrayList<>();
    }

    public void checkTypes(ASTMachine machine) {
        System.out.println();
        machine.jjtAccept(new NestedASTTypeChecker(), null);
        if (!errors.isEmpty()) {
            throw new Error(errors.size() + " errors found:\n" + errors.stream().collect(Collectors.joining("\n", "\t", "")));
        }
    }

    private class NestedASTTypeChecker implements BParserVisitor {

        private Map<String, AType> symbolsTable;
        private Stack<AType> setTypes;

        public NestedASTTypeChecker() {
            this.symbolsTable = new LinkedHashMap<>();
            this.setTypes = new Stack<>();
        }

        private void handleError(SourceCoordinates coordinates, String error) {
            throw new Error("l." + coordinates.getLineStart() + ", c." + coordinates.getColumnStart() + ": " + error);
        }

        private AType checkTypeMatches(Node node, AType... expectedTypes) {
            AType actualType = (AType) node.jjtAccept(this, null);
            if (!Arrays.asList(expectedTypes).contains(actualType)) {
                handleError(((SimpleNode) node).getSourceCoordinates(), "Expected expression of type among " + Arrays.toString(expectedTypes) + "\", but expression of type \"" + actualType + "\" found.");
                return null;
            } else {
                return actualType;
            }
        }

        @Override
        public Object visit(SimpleNode node, Map<Object, Object> data) {
            throw new Error("Unable to check types for abstract node \"" + node + "\".");
        }

        @Override
        public Object visit(ASTMachine node, Map<Object, Object> data) {
            node.childrenAccept(this, data);
            return null;
        }

        @Override
        public Object visit(ASTConstDefs node, Map<Object, Object> data) {
            node.childrenAccept(this, data);
            return null;
        }

        @Override
        public Object visit(ASTConstDef node, Map<Object, Object> data) {
            AType expectedType = (AType) node.jjtGetChild(0).jjtAccept(this, data);
            symbolsTable.put(((SimpleNode) node.jjtGetChild(1)).jjtGetValue().toString(), expectedType);
            checkTypeMatches(node.jjtGetChild(2), expectedType);
            return null;
        }

        @Override
        public Object visit(ASTSetDefs node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTSetDef node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTVarDefs node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTVarDef node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTFunDefs node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTFunDef node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTInvariant node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTSubstitution node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTEvents node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTEvent node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTSkip node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTVarAssignment node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTFunAssignment node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTSelect node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTIfThenElse node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTChoice node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTQuantifiedSymbolsDefs node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTAny node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTSequence node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTDef node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTExpr node, Map<Object, Object> data) {
            return node.jjtGetChild(0).jjtAccept(this, data);
        }

        @Override
        public Object visit(ASTEquiv node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTImplies node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTOr node, Map<Object, Object> data) {
            for (Node child : node.getChildren()) {
                checkTypeMatches(child, getBoolType());
            }
            return getBoolType();
        }

        @Override
        public Object visit(ASTAnd node, Map<Object, Object> data) {
            for (Node child : node.getChildren()) {
                checkTypeMatches(child, getBoolType());
            }
            return getBoolType();
        }

        @Override
        public Object visit(ASTEq node, Map<Object, Object> data) {
            /*if (node.jjtGetChild(0) instanceof ASTSet || node.jjtGetChild(1) instanceof ASTSet) {
            } else {
                AType leftType = (AType) node.jjtGetChild(0).jjtAccept(this, data);
                AType rightType = (AType) node.jjtGetChild(1).jjtAccept(this, data);
                System.out.println(leftType.equals(rightType));
            }*/
            return getBoolType();
        }

        @Override
        public Object visit(ASTNEq node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTIn node, Map<Object, Object> data) {
            return getBoolType();
        }

        @Override
        public Object visit(ASTNotIn node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTLT node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTLE node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTGT node, Map<Object, Object> data) {
            checkTypeMatches(node.jjtGetChild(0), getIntType(), getRealType());
            checkTypeMatches(node.jjtGetChild(1), getIntType(), getRealType());
            return getBoolType();
        }

        @Override
        public Object visit(ASTGE node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTPlus node, Map<Object, Object> data) {
            AType type = getIntType();
            for (Node child : node.getChildren()) {
                AType childType = checkTypeMatches(child, getIntType(), getRealType());
                if (childType == getRealType()) {
                    type = getRealType();
                }
            }
            return type;
        }

        @Override
        public Object visit(ASTMinus node, Map<Object, Object> data) {
            AType type = getIntType();
            for (Node child : node.getChildren()) {
                AType childType = checkTypeMatches(child, getIntType(), getRealType());
                if (childType == getRealType()) {
                    type = getRealType();
                }
            }
            return type;
        }

        @Override
        public Object visit(ASTTimes node, Map<Object, Object> data) {
            AType type = getIntType();
            for (Node child : node.getChildren()) {
                AType childType = checkTypeMatches(child, getIntType(), getRealType());
                if (childType == getRealType()) {
                    type = getRealType();
                }
            }
            return type;
        }

        @Override
        public Object visit(ASTDiv node, Map<Object, Object> data) {
            AType type = getIntType();
            for (Node child : node.getChildren()) {
                AType childType = checkTypeMatches(child, getIntType(), getRealType());
                if (childType == getRealType()) {
                    type = getRealType();
                }
            }
            return type;
        }

        @Override
        public Object visit(ASTMod node, Map<Object, Object> data) {
            AType type = getIntType();
            for (Node child : node.getChildren()) {
                AType childType = checkTypeMatches(child, getIntType(), getRealType());
                if (childType == getRealType()) {
                    type = getRealType();
                }
            }
            return type;
        }

        @Override
        public Object visit(ASTNot node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTUMinus node, Map<Object, Object> data) {
            return checkTypeMatches(node.jjtGetChild(0), getIntType(), getRealType());
        }

        @Override
        public Object visit(ASTFunCall node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTFalse node, Map<Object, Object> data) {
            return getBoolType();
        }

        @Override
        public Object visit(ASTTrue node, Map<Object, Object> data) {
            return getBoolType();
        }

        @Override
        public Object visit(ASTDouble node, Map<Object, Object> data) {
            return getRealType();
        }

        @Override
        public Object visit(ASTInt node, Map<Object, Object> data) {
            return getIntType();
        }

        @Override
        public Object visit(ASTExists node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTForAll node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTIdentifier node, Map<Object, Object> data) {
            String identifier = node.jjtGetValue().toString();
            if (!symbolsTable.containsKey(identifier)) {
                handleError(node.getSourceCoordinates(), "Symbol \"" + identifier + "\" was not declared in this scope.");
                return null;
            }
            return symbolsTable.get(identifier);
        }

        @Override
        public Object visit(ASTSet node, Map<Object, Object> data) {
            SetType type = (SetType) setTypes.pop();
            for (Node child : node.getChildren()) {
                setTypes.push(type.getElementsType());
                checkTypeMatches(child, type.getElementsType() instanceof RealType ? new AType[]{getIntType(), getRealType()} : new AType[]{type.getElementsType()});
            }
            return type;
        }

        @Override
        public Object visit(ASTRange node, Map<Object, Object> data) {
            checkTypeMatches(node.jjtGetChild(0), getIntType());
            checkTypeMatches(node.jjtGetChild(1), getIntType());
            if (!setTypes.peek().equals(getSetType(getIntType())) && !setTypes.peek().equals(getSetType(getRealType()))) {
                handleError(node.getSourceCoordinates(), "Expected expression of type \"" + setTypes.peek() + "\" but expression of type \"" + getSetType(getIntType()) + "\" or \"" + getSetType(getRealType()) + "\" found.");
                return null;
            }
            return setTypes.peek();
        }

        @Override
        public Object visit(ASTBoolType node, Map<Object, Object> data) {
            return getBoolType();
        }

        @Override
        public Object visit(ASTIntType node, Map<Object, Object> data) {
            return getIntType();
        }

        @Override
        public Object visit(ASTArithType node, Map<Object, Object> data) {
            return node.jjtGetChild(0).jjtAccept(this, data);
        }

        @Override
        public Object visit(ASTRealType node, Map<Object, Object> data) {
            return getRealType();
        }

        @Override
        public Object visit(ASTSetType node, Map<Object, Object> data) {
            return setTypes.push(getSetType((AType) node.jjtGetChild(0).jjtAccept(this, data)));
        }

        @Override
        public Object visit(ASTStringType node, Map<Object, Object> data) {
            return null;
        }

    }

}
