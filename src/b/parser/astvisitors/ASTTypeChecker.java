package b.parser.astvisitors;

import b.lang.types.AType;
import b.lang.types.SetType;
import b.lang.types.Types;
import b.parser.*;

import java.util.*;
import java.util.stream.Collectors;

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

        private Stack<AType> types;
        private Map<String, AType> symbolsTable;

        public NestedASTTypeChecker() {
            this.types = new Stack<>();
            this.symbolsTable = new LinkedHashMap<>();
        }

        private void checkMatchExpectedType(AType actualType, SourceCoordinates coordinates) {
            checkMatchExpectedType(Collections.singletonList(actualType), coordinates);
        }

        private void checkMatchExpectedType(List<AType> actualTypes, SourceCoordinates coordinates) {
            AType expectedType = types.pop();
            if (!actualTypes.contains(expectedType)) {
                handleError(coordinates, "Expected expression of type \"" + expectedType + "\" but expression of type among \"" + actualTypes + "\" found.");
            }
        }

        private void handleError(SourceCoordinates coordinates, String error) {
            // TODO: replace by "errors.add..."
            throw new Error("l." + coordinates.getLineStart() + ", c." + coordinates.getColumnStart() + ": " + error);
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
            node.childrenAccept(this, null);
            return null;
        }

        @Override
        public Object visit(ASTConstDef node, Map<Object, Object> data) {
            node.jjtGetChild(0).jjtAccept(this, data);
            AType type = types.peek();
            node.jjtGetChild(2).jjtAccept(this, data);
            symbolsTable.put(((ASTIdentifier) node.jjtGetChild(1)).jjtGetValue().toString(), type);
            System.out.println(types);
            System.out.println(symbolsTable);
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
            node.childrenAccept(this, data);
            return null;
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
                types.push(Types.getBoolType());
                child.jjtAccept(this, data);
            }
            checkMatchExpectedType(Types.getBoolType(), node.getSourceCoordinates());
            return null;
        }

        @Override
        public Object visit(ASTAnd node, Map<Object, Object> data) {
            for (Node child : node.getChildren()) {
                types.push(Types.getBoolType());
                child.jjtAccept(this, data);
            }
            checkMatchExpectedType(Types.getBoolType(), node.getSourceCoordinates());
            return null;
        }

        @Override
        public Object visit(ASTEq node, Map<Object, Object> data) {
            // TODO : currently only works for equality between reals and integers
            for (Node child : node.getChildren()) {
                types.push(Types.getRealType());
                child.jjtAccept(this, data);
            }
            checkMatchExpectedType(Types.getBoolType(), node.getSourceCoordinates());
            return null;
        }

        @Override
        public Object visit(ASTNEq node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTIn node, Map<Object, Object> data) {
            return null;
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
            checkMatchExpectedType(Types.getBoolType(), node.getSourceCoordinates());
            for (Node child : node.getChildren()) {
                types.push(Types.getRealType());
                child.jjtAccept(this, data);
            }
            return null;
        }

        @Override
        public Object visit(ASTGE node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTPlus node, Map<Object, Object> data) {
            for (Node child : node.getChildren()) {
                types.push(Types.getRealType());
                child.jjtAccept(this, data);
            }
            checkMatchExpectedType(Arrays.asList(Types.getIntType(), Types.getRealType()), node.getSourceCoordinates());
            return null;
        }

        @Override
        public Object visit(ASTMinus node, Map<Object, Object> data) {
            for (Node child : node.getChildren()) {
                types.push(Types.getRealType());
                child.jjtAccept(this, data);
            }
            checkMatchExpectedType(Arrays.asList(Types.getIntType(), Types.getRealType()), node.getSourceCoordinates());
            return null;
        }

        @Override
        public Object visit(ASTTimes node, Map<Object, Object> data) {
            for (Node child : node.getChildren()) {
                types.push(Types.getRealType());
                child.jjtAccept(this, data);
            }
            checkMatchExpectedType(Arrays.asList(Types.getIntType(), Types.getRealType()), node.getSourceCoordinates());
            return null;
        }

        @Override
        public Object visit(ASTDiv node, Map<Object, Object> data) {
            for (Node child : node.getChildren()) {
                types.push(Types.getRealType());
                child.jjtAccept(this, data);
            }
            checkMatchExpectedType(Arrays.asList(Types.getIntType(), Types.getRealType()), node.getSourceCoordinates());
            return null;
        }

        @Override
        public Object visit(ASTMod node, Map<Object, Object> data) {
            for (Node child : node.getChildren()) {
                types.push(Types.getRealType());
                child.jjtAccept(this, data);
            }
            checkMatchExpectedType(Arrays.asList(Types.getIntType(), Types.getRealType()), node.getSourceCoordinates());
            return null;
        }

        @Override
        public Object visit(ASTNot node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTUMinus node, Map<Object, Object> data) {
            types.push(Types.getRealType());
            node.jjtGetChild(0).jjtAccept(this, data);
            checkMatchExpectedType(Types.getRealType(), node.getSourceCoordinates());
            return null;
        }

        @Override
        public Object visit(ASTFunCall node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTFalse node, Map<Object, Object> data) {
            checkMatchExpectedType(Types.getBoolType(), node.getSourceCoordinates());
            return null;
        }

        @Override
        public Object visit(ASTTrue node, Map<Object, Object> data) {
            checkMatchExpectedType(Types.getBoolType(), node.getSourceCoordinates());
            return null;
        }

        @Override
        public Object visit(ASTDouble node, Map<Object, Object> data) {
            checkMatchExpectedType(Types.getRealType(), node.getSourceCoordinates());
            return null;
        }

        @Override
        public Object visit(ASTInt node, Map<Object, Object> data) {
            checkMatchExpectedType(Arrays.asList(Types.getIntType(), Types.getRealType()), node.getSourceCoordinates());
            return null;
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
            if (symbolsTable.containsKey(node.jjtGetValue().toString())) {
                AType type = symbolsTable.get(node.jjtGetValue().toString());
                if (type.equals(Types.getIntType())) {
                    checkMatchExpectedType(Arrays.asList(Types.getIntType(), Types.getRealType()), node.getSourceCoordinates());
                } else {
                    checkMatchExpectedType(type, node.getSourceCoordinates());
                }
            } else {
                handleError(node.getSourceCoordinates(), "Symbol \"" + node.jjtGetValue() + "\" was not defined in this scope.");
            }
            return null;
        }

        @Override
        public Object visit(ASTSet node, Map<Object, Object> data) {
            if (node.jjtGetNumChildren() != 0) {
                for (Node child : node.getChildren()) {
                    if (types.peek() instanceof SetType) {
                        types.push(((SetType) types.peek()).getElementsType());
                    } else {
                        types.push(types.peek());
                    }
                    child.jjtAccept(this, data);
                }
            }
            AType expectedType = types.pop();
            if (!(expectedType instanceof SetType)) {
                handleError(node.getSourceCoordinates(), "Expected expression of type \"" + expectedType + "\" but expression of type \"set<?>\".");
            }
            return null;
        }

        @Override
        public Object visit(ASTRange node, Map<Object, Object> data) {
            types.push(Types.getIntType());
            node.jjtGetChild(0).jjtAccept(this, data);
            types.push(Types.getIntType());
            node.jjtGetChild(1).jjtAccept(this, data);
            checkMatchExpectedType(Arrays.asList(Types.getSetType(Types.getIntType()), Types.getSetType(Types.getRealType())), node.getSourceCoordinates());
            return null;
        }

        @Override
        public Object visit(ASTBoolType node, Map<Object, Object> data) {
            return types.push(Types.getBoolType());
        }

        @Override
        public Object visit(ASTIntType node, Map<Object, Object> data) {
            return types.push(Types.getIntType());
        }

        @Override
        public Object visit(ASTArithType node, Map<Object, Object> data) {
            return node.jjtGetChild(0).jjtAccept(this, data);
        }

        @Override
        public Object visit(ASTRealType node, Map<Object, Object> data) {
            return types.push(Types.getRealType());
        }

        @Override
        public Object visit(ASTSetType node, Map<Object, Object> data) {
            AType type = Types.getSetType((AType) node.jjtGetChild(0).jjtAccept(this, data));
            types.pop();
            return types.push(type);
        }

        @Override
        public Object visit(ASTStringType node, Map<Object, Object> data) {
            return types.push(Types.getStringType());
        }

    }

}
