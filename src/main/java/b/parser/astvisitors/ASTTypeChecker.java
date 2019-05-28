package b.parser.astvisitors;

import b.lang.types.AType;
import b.parser.*;
import tools.Tuple;

import java.util.*;

import static b.lang.types.Types.*;

/**
 * Created by gvoiron on 13/05/19.
 * Time : 14:49
 */
public final class ASTTypeChecker {

    private final LinkedHashMap<String, Tuple<AType, AType>> symbolsTable;
    private final List<String> errors;

    public ASTTypeChecker() {
        this.symbolsTable = new LinkedHashMap<>();
        this.errors = new ArrayList<>();
    }

    public ASTTypeCheckerResult checkTypes(ASTMachine machine) {
        machine.jjtAccept(new NestedASTTypeChecker(), null);
        return new ASTTypeCheckerResult(symbolsTable, errors);
    }

    private class NestedASTTypeChecker implements BParserVisitor {

        private final List<String> consts;
        private final List<String> vars;
        private final List<String> funs;
        private LinkedHashMap<String, Tuple<AType, AType>> quantifiedSymbolsTable;
        private boolean useQuantifiedSymbols;

        public NestedASTTypeChecker() {
            this.consts = new ArrayList<>();
            this.vars = new ArrayList<>();
            this.funs = new ArrayList<>();
            this.useQuantifiedSymbols = false;
            this.quantifiedSymbolsTable = new LinkedHashMap<>();
        }

        private void handleError(SourceCoordinates coordinates, String error) {
            errors.add("l." + coordinates.getLineStart() + ", c." + coordinates.getColumnStart() + ": " + error);
        }

        private AType checkTypeMatches(Node node, AType... expectedTypes) {
            AType actualType = (AType) node.jjtAccept(this, null);
            if (actualType != getNullType()) {
                for (AType expectedType : expectedTypes) {
                    if (actualType.instanceOf(expectedType)) {
                        return expectedType;
                    }
                }
                if (expectedTypes.length != 1 || !expectedTypes[0].equals(getNullType())) {
                    handleError(((SimpleNode) node).getSourceCoordinates(), "Expected expression " + node + " to be of type among " + Arrays.toString(expectedTypes) + ", but has type \"" + actualType + "\".");
                }
            }
            return getNullType();
        }

        @Override
        public Object visit(SimpleNode node, Map<Object, Object> data) {
            handleError(node.getSourceCoordinates(), "Unable to check types for abstract node \"" + node + "\".");
            return null;
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
            String name = ((SimpleNode) node.jjtGetChild(1)).jjtGetValue().toString();
            if (symbolsTable.containsKey(name)) {
                handleError(((SimpleNode) node.jjtGetChild(1)).getSourceCoordinates(), "Symbol \"" + name + "\" was already declared in this scope.");
            } else {
                symbolsTable.put(name, new Tuple<>(getNullType(), expectedType));
                consts.add(name);
            }
            checkTypeMatches(node.jjtGetChild(2), expectedType);
            return null;
        }

        @Override
        public Object visit(ASTSetDefs node, Map<Object, Object> data) {
            node.childrenAccept(this, data);
            return null;
        }

        @Override
        public Object visit(ASTSetDef node, Map<Object, Object> data) {
            AType expectedType = (AType) node.jjtGetChild(0).jjtAccept(this, data);
            String name = ((SimpleNode) node.jjtGetChild(1)).jjtGetValue().toString();
            if (symbolsTable.containsKey(name)) {
                handleError(((SimpleNode) node.jjtGetChild(1)).getSourceCoordinates(), "Symbol \"" + name + "\" was already declared in this scope.");
            } else {
                symbolsTable.put(name, new Tuple<>(getNullType(), expectedType));
            }
            checkTypeMatches(node.jjtGetChild(2), expectedType);
            return null;
        }

        @Override
        public Object visit(ASTVarDefs node, Map<Object, Object> data) {
            node.childrenAccept(this, data);
            return null;
        }

        @Override
        public Object visit(ASTVarDef node, Map<Object, Object> data) {
            AType expectedType = (AType) node.jjtGetChild(0).jjtAccept(this, data);
            String name = ((SimpleNode) node.jjtGetChild(1)).jjtGetValue().toString();
            if (symbolsTable.containsKey(name)) {
                handleError(((SimpleNode) node.jjtGetChild(1)).getSourceCoordinates(), "Symbol \"" + name + "\" was already declared in this scope.");
            } else {
                symbolsTable.put(name, new Tuple<>(getNullType(), expectedType));
                vars.add(name);
            }
            checkTypeMatches(node.jjtGetChild(2), getSetType(expectedType));
            return null;
        }

        @Override
        public Object visit(ASTFunDefs node, Map<Object, Object> data) {
            node.childrenAccept(this, data);
            return null;
        }

        @Override
        public Object visit(ASTFunDef node, Map<Object, Object> data) {
            AType expectedDomainType = (AType) node.jjtGetChild(0).jjtAccept(this, data);
            AType expectedCodomainType = (AType) node.jjtGetChild(1).jjtAccept(this, data);
            String name = ((SimpleNode) node.jjtGetChild(2)).jjtGetValue().toString();
            if (symbolsTable.containsKey(name)) {
                handleError(((SimpleNode) node.jjtGetChild(2)).getSourceCoordinates(), "Symbol \"" + name + "\" was already declared in this scope.");
            } else {
                symbolsTable.put(name, new Tuple<>(expectedDomainType, expectedCodomainType));
                funs.add(name);
            }
            checkTypeMatches(node.jjtGetChild(3), getSetType(expectedDomainType));
            checkTypeMatches(node.jjtGetChild(4), getSetType(expectedCodomainType));
            return null;
        }

        @Override
        public Object visit(ASTInvariant node, Map<Object, Object> data) {
            return checkTypeMatches(node.jjtGetChild(0), getBoolType());
        }

        @Override
        public Object visit(ASTInitialisation node, Map<Object, Object> data) {
            return node.jjtGetChild(0).jjtAccept(this, data);
        }

        @Override
        public Object visit(ASTEvents node, Map<Object, Object> data) {
            node.childrenAccept(this, data);
            return null;
        }

        @Override
        public Object visit(ASTEvent node, Map<Object, Object> data) {
            node.jjtGetChild(1).jjtAccept(this, data);
            return null;
        }

        @Override
        public Object visit(ASTSkip node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTVarAssignment node, Map<Object, Object> data) {
            String identifier = ((SimpleNode) node.jjtGetChild(0)).jjtGetValue().toString();
            if (quantifiedSymbolsTable.containsKey(identifier)) {
                handleError(node.getSourceCoordinates(), "Attempting to assign value \"" + node.jjtGetChild(1) + "\" to quantified symbol \"" + identifier + "\".");
            } else if (consts.contains(identifier)) {
                handleError(node.getSourceCoordinates(), "Attempting to assign value \"" + node.jjtGetChild(1) + "\" to constant \"" + identifier + "\".");
            } else if (!vars.contains(identifier)) {
                handleError(node.getSourceCoordinates(), "Symbol \"" + identifier + "\" is used as a variable but was not declared as such.");
            } else {
                checkTypeMatches(node.jjtGetChild(1), (AType) node.jjtGetChild(0).jjtAccept(this, data));
            }
            return null;
        }

        @Override
        public Object visit(ASTFunAssignment node, Map<Object, Object> data) {
            String identifier = ((SimpleNode) node.jjtGetChild(0)).jjtGetValue().toString();
            if (quantifiedSymbolsTable.containsKey(identifier)) {
                handleError(node.getSourceCoordinates(), "Attempting to assign value \"" + node.jjtGetChild(1) + "\" to quantified symbol \"" + identifier + "\".");
            } else if (!funs.contains(identifier)) {
                handleError(node.getSourceCoordinates(), "Symbol \"" + identifier + "\" is used as a function but was not declared as such.");
            } else {
                checkTypeMatches(node.jjtGetChild(1), symbolsTable.get(identifier).getFirst());
                checkTypeMatches(node.jjtGetChild(2), symbolsTable.get(identifier).getSecond());
            }
            return null;
        }

        @Override
        public Object visit(ASTSelect node, Map<Object, Object> data) {
            checkTypeMatches(node.jjtGetChild(0), getBoolType());
            node.jjtGetChild(1).jjtAccept(this, data);
            return null;
        }

        @Override
        public Object visit(ASTIfThenElse node, Map<Object, Object> data) {
            checkTypeMatches(node.jjtGetChild(0), getBoolType());
            node.jjtGetChild(1).jjtAccept(this, data);
            node.jjtGetChild(2).jjtAccept(this, data);
            return null;
        }

        @Override
        public Object visit(ASTChoice node, Map<Object, Object> data) {
            node.childrenAccept(this, data);
            return null;
        }

        @Override
        public Object visit(ASTAny node, Map<Object, Object> data) {
            useQuantifiedSymbols = true;
            LinkedHashMap<String, Tuple<AType, AType>> oldQuantifiedSymbolsTable = new LinkedHashMap<>(quantifiedSymbolsTable);
            node.jjtGetChild(0).jjtAccept(this, data);
            checkTypeMatches(node.jjtGetChild(1), getBoolType());
            node.jjtGetChild(2).jjtAccept(this, data);
            useQuantifiedSymbols = false;
            quantifiedSymbolsTable = oldQuantifiedSymbolsTable;
            return null;
        }

        @Override
        public Object visit(ASTQuantifiedVarDef node, Map<Object, Object> data) {
            AType expectedType = (AType) node.jjtGetChild(0).jjtAccept(this, data);
            String name = ((SimpleNode) node.jjtGetChild(1)).jjtGetValue().toString();
            if (quantifiedSymbolsTable.containsKey(name)) {
                handleError(((SimpleNode) node.jjtGetChild(1)).getSourceCoordinates(), "Quantified symbol \"" + name + "\" was already declared in this scope.");
            } else {
                quantifiedSymbolsTable.put(name, new Tuple<>(getNullType(), expectedType));
            }
            checkTypeMatches(node.jjtGetChild(2), getSetType(expectedType));
            return null;
        }

        @Override
        public Object visit(ASTQuantifiedFunDef node, Map<Object, Object> data) {
            AType expectedDomainType = (AType) node.jjtGetChild(0).jjtAccept(this, data);
            AType expectedCodomainType = (AType) node.jjtGetChild(1).jjtAccept(this, data);
            String name = ((SimpleNode) node.jjtGetChild(2)).jjtGetValue().toString();
            if (quantifiedSymbolsTable.containsKey(name)) {
                handleError(((SimpleNode) node.jjtGetChild(2)).getSourceCoordinates(), "Quantified symbol \"" + name + "\" was already declared in this scope.");
            } else {
                quantifiedSymbolsTable.put(name, new Tuple<>(expectedDomainType, expectedCodomainType));
            }
            checkTypeMatches(node.jjtGetChild(3), getSetType(expectedDomainType));
            checkTypeMatches(node.jjtGetChild(4), getSetType(expectedCodomainType));
            return null;
        }

        @Override
        public Object visit(ASTQuantifiedSymbolsDefs node, Map<Object, Object> data) {
            node.childrenAccept(this, data);
            return null;
        }

        @Override
        public Object visit(ASTSequence node, Map<Object, Object> data) {
            node.childrenAccept(this, data);
            return null;
        }

        @Override
        public Object visit(ASTExpr node, Map<Object, Object> data) {
            return node.jjtGetChild(0).jjtAccept(this, data);
        }

        @Override
        public Object visit(ASTEquiv node, Map<Object, Object> data) {
            for (Node child : node.getChildren()) {
                checkTypeMatches(child, getBoolType());
            }
            return getBoolType();
        }

        @Override
        public Object visit(ASTImplies node, Map<Object, Object> data) {
            for (Node child : node.getChildren()) {
                checkTypeMatches(child, getBoolType());
            }
            return getBoolType();
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
            AType expectedType = (AType) node.jjtGetChild(0).jjtAccept(this, data);
            checkTypeMatches(node.jjtGetChild(1), expectedType);
            return getBoolType();
        }

        @Override
        public Object visit(ASTNEq node, Map<Object, Object> data) {
            AType expectedType = (AType) node.jjtGetChild(0).jjtAccept(this, data);
            checkTypeMatches(node.jjtGetChild(1), expectedType);
            return getBoolType();
        }

        @Override
        public Object visit(ASTIn node, Map<Object, Object> data) {
            AType expectedType = (AType) node.jjtGetChild(0).jjtAccept(this, data);
            checkTypeMatches(node.jjtGetChild(1), getSetType(expectedType));
            return getBoolType();
        }

        @Override
        public Object visit(ASTNotIn node, Map<Object, Object> data) {
            AType expectedType = (AType) node.jjtGetChild(0).jjtAccept(this, data);
            checkTypeMatches(node.jjtGetChild(1), getSetType(expectedType));
            return getBoolType();
        }

        @Override
        public Object visit(ASTLT node, Map<Object, Object> data) {
            checkTypeMatches(node.jjtGetChild(0), getIntType(), getRealType());
            checkTypeMatches(node.jjtGetChild(1), getIntType(), getRealType());
            return getBoolType();
        }

        @Override
        public Object visit(ASTLE node, Map<Object, Object> data) {
            checkTypeMatches(node.jjtGetChild(0), getIntType(), getRealType());
            checkTypeMatches(node.jjtGetChild(1), getIntType(), getRealType());
            return getBoolType();
        }

        @Override
        public Object visit(ASTGT node, Map<Object, Object> data) {
            checkTypeMatches(node.jjtGetChild(0), getIntType(), getRealType());
            checkTypeMatches(node.jjtGetChild(1), getIntType(), getRealType());
            return getBoolType();
        }

        @Override
        public Object visit(ASTGE node, Map<Object, Object> data) {
            checkTypeMatches(node.jjtGetChild(0), getIntType(), getRealType());
            checkTypeMatches(node.jjtGetChild(1), getIntType(), getRealType());
            return getBoolType();
        }

        @Override
        public Object visit(ASTPlus node, Map<Object, Object> data) {
            AType type = getIntType();
            for (Node child : node.getChildren()) {
                AType childType = checkTypeMatches(child, getIntType(), getRealType());
                if (childType != null && type.instanceOf(childType)) {
                    type = childType;
                }
            }
            return type;
        }

        @Override
        public Object visit(ASTMinus node, Map<Object, Object> data) {
            AType type = getIntType();
            for (Node child : node.getChildren()) {
                AType childType = checkTypeMatches(child, getIntType(), getRealType());
                if (childType != null && type.instanceOf(childType)) {
                    type = childType;
                }
            }
            return type;
        }

        @Override
        public Object visit(ASTTimes node, Map<Object, Object> data) {
            AType type = getIntType();
            for (Node child : node.getChildren()) {
                AType childType = checkTypeMatches(child, getIntType(), getRealType());
                if (childType != null && type.instanceOf(childType)) {
                    type = childType;
                }
            }
            return type;
        }

        @Override
        public Object visit(ASTDiv node, Map<Object, Object> data) {
            AType type = getIntType();
            for (Node child : node.getChildren()) {
                AType childType = checkTypeMatches(child, getIntType(), getRealType());
                if (childType != null && type.instanceOf(childType)) {
                    type = childType;
                }
            }
            return type;
        }

        @Override
        public Object visit(ASTMod node, Map<Object, Object> data) {
            AType type = getIntType();
            for (Node child : node.getChildren()) {
                AType childType = checkTypeMatches(child, getIntType(), getRealType());
                if (childType != null && type.instanceOf(childType)) {
                    type = childType;
                }
            }
            return type;
        }

        @Override
        public Object visit(ASTNot node, Map<Object, Object> data) {
            checkTypeMatches(node.jjtGetChild(0), getBoolType());
            return getBoolType();
        }

        @Override
        public Object visit(ASTUMinus node, Map<Object, Object> data) {
            return checkTypeMatches(node.jjtGetChild(0), getIntType(), getRealType());
        }

        @Override
        public Object visit(ASTFunCall node, Map<Object, Object> data) {
            String identifier = ((SimpleNode) node.jjtGetChild(0)).jjtGetValue().toString();
            if (useQuantifiedSymbols && quantifiedSymbolsTable.containsKey(identifier)) {
                return quantifiedSymbolsTable.get(identifier).getSecond();
            }
            if (!symbolsTable.containsKey(identifier)) {
                handleError(node.getSourceCoordinates(), "Symbol \"" + identifier + "\" was not declared in this scope.");
                return getNullType();
            }
            return symbolsTable.get(identifier).getSecond();
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
            useQuantifiedSymbols = true;
            LinkedHashMap<String, Tuple<AType, AType>> oldQuantifiedSymbolsTable = new LinkedHashMap<>(quantifiedSymbolsTable);
            node.jjtGetChild(0).jjtAccept(this, data);
            checkTypeMatches(node.jjtGetChild(1), getBoolType());
            useQuantifiedSymbols = false;
            quantifiedSymbolsTable = oldQuantifiedSymbolsTable;
            return getBoolType();
        }

        @Override
        public Object visit(ASTForAll node, Map<Object, Object> data) {
            useQuantifiedSymbols = true;
            LinkedHashMap<String, Tuple<AType, AType>> oldQuantifiedSymbolsTable = new LinkedHashMap<>(quantifiedSymbolsTable);
            node.jjtGetChild(0).jjtAccept(this, data);
            checkTypeMatches(node.jjtGetChild(1), getBoolType());
            useQuantifiedSymbols = false;
            quantifiedSymbolsTable = oldQuantifiedSymbolsTable;
            return getBoolType();
        }

        @Override
        public Object visit(ASTString node, Map<Object, Object> data) {
            return getStringType();
        }

        @Override
        public Object visit(ASTIdentifier node, Map<Object, Object> data) {
            String identifier = node.jjtGetValue().toString();
            if (useQuantifiedSymbols && quantifiedSymbolsTable.containsKey(identifier)) {
                return quantifiedSymbolsTable.get(identifier).getSecond();
            }
            if (!symbolsTable.containsKey(identifier)) {
                handleError(node.getSourceCoordinates(), "Symbol \"" + identifier + "\" was not declared in this scope.");
                return getNullType();
            }
            return symbolsTable.get(identifier).getSecond();
        }

        @Override
        public Object visit(ASTSet node, Map<Object, Object> data) {
            if (node.jjtGetNumChildren() == 0) {
                return getSetType(getObjectType());
            } else {
                AType elementsType = getNullType();
                for (Node child : node.getChildren()) {
                    AType childType = (AType) child.jjtAccept(this, data);
                    if (elementsType.equals(getNullType()) || elementsType.instanceOf(childType)) {
                        elementsType = childType;
                    } else if (!childType.instanceOf(elementsType)) {
                        checkTypeMatches(child, elementsType);
                    }
                }
                return getSetType(elementsType);
            }
        }

        @Override
        public Object visit(ASTRange node, Map<Object, Object> data) {
            checkTypeMatches(node.jjtGetChild(0), getIntType());
            checkTypeMatches(node.jjtGetChild(1), getIntType());
            return getSetType(getIntType());
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
            return getSetType((AType) node.jjtGetChild(0).jjtAccept(this, data));
        }

        @Override
        public Object visit(ASTStringType node, Map<Object, Object> data) {
            return getStringType();
        }

    }

    public final class ASTTypeCheckerResult {

        private final Map<String, Tuple<AType, AType>> symbolsTable;
        private final List<String> errors;

        public ASTTypeCheckerResult(Map<String, Tuple<AType, AType>> symbolsTable, List<String> errors) {
            this.symbolsTable = symbolsTable;
            this.errors = errors;
        }

        public Map<String, Tuple<AType, AType>> getSymbolsTable() {
            return symbolsTable;
        }

        public List<String> getErrors() {
            return errors;
        }

    }

}
