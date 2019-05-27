package b.parser.astvisitors;

import b.lang.Event;
import b.lang.Machine;
import b.lang.defs.ConstDef;
import b.lang.defs.FunDef;
import b.lang.defs.SetDef;
import b.lang.defs.VarDef;
import b.lang.exprs.ASymbol;
import b.lang.exprs.IExpr;
import b.lang.exprs.arith.IArithExpr;
import b.lang.exprs.arith.Int;
import b.lang.exprs.arith.Real;
import b.lang.exprs.bool.Eq;
import b.lang.exprs.bool.IBoolExpr;
import b.lang.exprs.set.ISetExpr;
import b.lang.exprs.set.Range;
import b.lang.exprs.set.Set;
import b.lang.exprs.string.StringVal;
import b.lang.substitutions.*;
import b.lang.types.AType;
import b.lang.types.SetType;
import b.parser.*;
import b.parser.astvisitors.ASTTypeChecker.ASTTypeCheckerResult;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static b.lang.types.Types.*;

/**
 * Created by gvoiron on 21/05/19.
 * Time : 03:29
 */
public final class ASTToBTranslator {

    private Machine machine;

    public final Machine translate(ASTMachine machineNode) {
        ASTTypeCheckerResult typeChecking = new ASTTypeChecker().checkTypes(machineNode);
        if (!typeChecking.getErrors().isEmpty()) {
            throw new Error("Unable to translate AST to B because the following errors occurred during type checking:\n" + typeChecking.getErrors().stream().collect(Collectors.joining("\n", "\t", "")));
        }
        machineNode.jjtAccept(new NestedASTToBTranslator(), null);
        return machine;
    }

    private final class NestedASTToBTranslator implements BParserVisitor {

        public NestedASTToBTranslator() {
        }

        @Override
        public Object visit(SimpleNode node, Map<Object, Object> data) {
            throw new Error("Unable to translate node of abstract type \"" + node + "\" to B language.");
        }

        @Override
        public Object visit(ASTMachine node, Map<Object, Object> data) {
            machine = new Machine(node.jjtGetValue().toString());
            node.jjtGetChild(0).jjtAccept(this, data);
            node.jjtGetChild(1).jjtAccept(this, data);
            node.jjtGetChild(2).jjtAccept(this, data);
            node.jjtGetChild(3).jjtAccept(this, data);
            machine.setInitialisation((ASubstitution) node.jjtGetChild(4).jjtAccept(this, data));
            node.jjtGetChild(5).jjtAccept(this, data);
            return machine;
        }

        @Override
        public Object visit(ASTConstDefs node, Map<Object, Object> data) {
            node.childrenAccept(this, data);
            return null;
        }

        @Override
        public Object visit(ASTConstDef node, Map<Object, Object> data) {
            AType type = (AType) node.jjtGetChild(0).jjtAccept(this, data);
            String name = ((SimpleNode) node.jjtGetChild(1)).jjtGetValue().toString();
            IExpr value = (IExpr) node.jjtGetChild(2).jjtAccept(this, data);
            machine.addConstDef(new ConstDef(type, name, value));
            return null;
        }

        @Override
        public Object visit(ASTSetDefs node, Map<Object, Object> data) {
            node.childrenAccept(this, data);
            return null;
        }

        @Override
        public Object visit(ASTSetDef node, Map<Object, Object> data) {
            SetType type = (SetType) node.jjtGetChild(0).jjtAccept(this, data);
            String name = ((SimpleNode) node.jjtGetChild(1)).jjtGetValue().toString();
            ISetExpr value = (ISetExpr) node.jjtGetChild(2).jjtAccept(this, data);
            machine.addSetDef(new SetDef(type, name, value));
            return null;
        }

        @Override
        public Object visit(ASTSetType node, Map<Object, Object> data) {
            return getSetType((AType) node.jjtGetChild(0).jjtAccept(this, data));
        }

        @Override
        public Object visit(ASTVarDefs node, Map<Object, Object> data) {
            node.childrenAccept(this, data);
            return null;
        }

        @Override
        public Object visit(ASTVarDef node, Map<Object, Object> data) {
            AType type = (AType) node.jjtGetChild(0).jjtAccept(this, data);
            String name = ((SimpleNode) node.jjtGetChild(1)).jjtGetValue().toString();
            ISetExpr domain = (ISetExpr) node.jjtGetChild(2).jjtAccept(this, data);
            machine.addVarDef(new VarDef(type, name, domain));
            return null;
        }

        @Override
        public Object visit(ASTFunDefs node, Map<Object, Object> data) {
            node.childrenAccept(this, data);
            return null;
        }

        @Override
        public Object visit(ASTFunDef node, Map<Object, Object> data) {
            AType type = (AType) node.jjtGetChild(0).jjtAccept(this, data);
            AType coType = (AType) node.jjtGetChild(1).jjtAccept(this, data);
            String name = ((SimpleNode) node.jjtGetChild(2)).jjtGetValue().toString();
            ISetExpr domain = (ISetExpr) node.jjtGetChild(3).jjtAccept(this, data);
            ISetExpr coDomain = (ISetExpr) node.jjtGetChild(4).jjtAccept(this, data);
            machine.addFunDef(new FunDef(type, coType, name, domain, coDomain));
            return null;
        }

        @Override
        public Object visit(ASTInvariant node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTSubstitution node, Map<Object, Object> data) {
            return node.jjtGetChild(0).jjtAccept(this, data);
        }

        @Override
        public Object visit(ASTEvents node, Map<Object, Object> data) {
            return node.childrenAccept(this, data);
        }

        @Override
        public Object visit(ASTEvent node, Map<Object, Object> data) {
            String name = ((SimpleNode) node.jjtGetChild(0)).jjtGetValue().toString();
            ASubstitution substitution = (ASubstitution) node.jjtGetChild(1).jjtAccept(this, data);
            machine.addEvent(new Event(name, substitution));
            return null;
        }

        @Override
        public Object visit(ASTSkip node, Map<Object, Object> data) {
            return new Skip();
        }

        @Override
        public Object visit(ASTVarAssignment node, Map<Object, Object> data) {
            ASymbol symbol = (ASymbol) node.jjtGetChild(0).jjtAccept(this, data);
            IExpr value = (IExpr) node.jjtGetChild(1).jjtAccept(this, data);
            return new VarAssignment(symbol, value);
        }

        @Override
        public Object visit(ASTFunAssignment node, Map<Object, Object> data) {
            ASymbol symbol = (ASymbol) node.jjtGetChild(0).jjtAccept(this, data);
            IExpr parameter = (IExpr) node.jjtGetChild(1).jjtAccept(this, data);
            IExpr value = (IExpr) node.jjtGetChild(2).jjtAccept(this, data);
            return new FunAssignment(symbol, parameter, value);
        }

        @Override
        public Object visit(ASTSelect node, Map<Object, Object> data) {
            IBoolExpr condition = (IBoolExpr) node.jjtGetChild(0).jjtAccept(this, data);
            ASubstitution substitution = (ASubstitution) node.jjtGetChild(1).jjtAccept(this, data);
            return new Select(condition, substitution);
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
            List<ASubstitution> substitutions = new ArrayList<>();
            for (Node child : node.getChildren()) {
                substitutions.add((ASubstitution) child.jjtAccept(this, data));
            }
            return new Sequence(substitutions);
        }

        @Override
        public Object visit(ASTQuantifiedVarDef node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTQuantifiedFunDef node, Map<Object, Object> data) {
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
            return null;
        }

        @Override
        public Object visit(ASTAnd node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTEq node, Map<Object, Object> data) {
            IExpr left = (IExpr) node.jjtGetChild(0).jjtAccept(this, data);
            IExpr right = (IExpr) node.jjtGetChild(1).jjtAccept(this, data);
            return new Eq(left, right);
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
            return null;
        }

        @Override
        public Object visit(ASTGE node, Map<Object, Object> data) {
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
        public Object visit(ASTNot node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTUMinus node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTFunCall node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTFalse node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTTrue node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTDouble node, Map<Object, Object> data) {
            return new Real(Double.parseDouble(node.jjtGetValue().toString()));
        }

        @Override
        public Object visit(ASTInt node, Map<Object, Object> data) {
            return new Int(Integer.parseInt(node.jjtGetValue().toString()));
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
        public Object visit(ASTString node, Map<Object, Object> data) {
            return new StringVal(node.jjtGetValue().toString());
        }

        @Override
        public Object visit(ASTIdentifier node, Map<Object, Object> data) {
            return machine.getSymbol(node.jjtGetValue().toString());
        }

        @Override
        public Object visit(ASTSet node, Map<Object, Object> data) {
            LinkedHashSet<IExpr> elements = new LinkedHashSet<>();
            for (Node child : node.getChildren()) {
                elements.add((IExpr) child.jjtAccept(this, data));
            }
            return new Set(elements);
        }

        @Override
        public Object visit(ASTRange node, Map<Object, Object> data) {
            IArithExpr lowerBound = (IArithExpr) node.jjtGetChild(0).jjtAccept(this, data);
            IArithExpr upperBound = (IArithExpr) node.jjtGetChild(1).jjtAccept(this, data);
            return new Range(lowerBound, upperBound);
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
        public Object visit(ASTStringType node, Map<Object, Object> data) {
            return getStringType();
        }

    }

}
