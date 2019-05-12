package b.parser.astvisitors;

import b.lang.Machine;
import b.lang.Symbol;
import b.lang.defs.ConstDef;
import b.lang.defs.FunDef;
import b.lang.defs.SetDef;
import b.lang.defs.VarDef;
import b.lang.exprs.AExpr;
import b.lang.exprs.bool.False;
import b.parser.*;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by gvoiron on 04/05/19.
 * Time : 22:35
 */
public final class ASTToBBuilder {

    public Machine build(ASTMachine machine) {
        return (Machine) machine.jjtAccept(new NestedASTToBBuilder(), null);
    }

    private class NestedASTToBBuilder implements BParserVisitor {

        @Override
        public Object visit(SimpleNode node, Map<Object, Object> data) {
            throw new Error("Unable to convert abstract node \"" + node + "\" to B object.");
        }

        @Override
        public Object visit(ASTMachine node, Map<Object, Object> data) {
            LinkedHashSet<ConstDef> constDefs = new LinkedHashSet<>();
            LinkedHashSet<SetDef> setDefs = new LinkedHashSet<>();
            LinkedHashSet<VarDef> varDefs = new LinkedHashSet<>();
            LinkedHashSet<FunDef> funDefs = new LinkedHashSet<>();
            for (Node child : node.getChildren()) {
                if (child.getClass() == ASTConstDefs.class) {
                    //noinspection unchecked
                    constDefs = (LinkedHashSet<ConstDef>) child.jjtAccept(this, data);
                } else if (child.getClass() == ASTSetDefs.class) {
                    //noinspection unchecked
                    setDefs = (LinkedHashSet<SetDef>) child.jjtAccept(this, data);
                } else if (child.getClass() == ASTVarDefs.class) {
                    //noinspection unchecked
                    varDefs = (LinkedHashSet<VarDef>) child.jjtAccept(this, data);
                } else if (child.getClass() == ASTFunDefs.class) {
                    //noinspection unchecked
                    funDefs = (LinkedHashSet<FunDef>) child.jjtAccept(this, data);
                }
            }
            return new Machine(constDefs, setDefs, varDefs, funDefs);
        }

        @Override
        public Object visit(ASTConstDefs node, Map<Object, Object> data) {
            return Arrays.stream(node.getChildren()).map(child -> child.jjtAccept(this, data)).collect(Collectors.toCollection(LinkedHashSet::new));
        }

        @Override
        public Object visit(ASTConstDef node, Map<Object, Object> data) {
            return new ConstDef(new TypeDeterminer().determineType(node.jjtGetChild(1)), (Symbol) node.jjtGetChild(0).jjtAccept(this, data), (AExpr) node.jjtGetChild(1).jjtAccept(this, data));
        }

        @Override
        public Object visit(ASTSetDefs node, Map<Object, Object> data) {
            return Arrays.stream(node.getChildren()).map(child -> child.jjtAccept(this, data)).collect(Collectors.toCollection(LinkedHashSet::new));
        }

        @Override
        public Object visit(ASTSetDef node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTVarDefs node, Map<Object, Object> data) {
            return Arrays.stream(node.getChildren()).map(child -> child.jjtAccept(this, data)).collect(Collectors.toCollection(LinkedHashSet::new));
        }

        @Override
        public Object visit(ASTVarDef node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTFunDefs node, Map<Object, Object> data) {
            return Arrays.stream(node.getChildren()).map(child -> child.jjtAccept(this, data)).collect(Collectors.toCollection(LinkedHashSet::new));
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
            return null;
        }

        @Override
        public Object visit(ASTAnd node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTEq node, Map<Object, Object> data) {
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
            return new False();
        }

        @Override
        public Object visit(ASTTrue node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTDouble node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTInt node, Map<Object, Object> data) {
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
            return new Symbol((String) node.jjtGetValue());
        }

        @Override
        public Object visit(ASTSet node, Map<Object, Object> data) {
            return null;
        }

        @Override
        public Object visit(ASTRange node, Map<Object, Object> data) {
            return null;
        }

    }

}
