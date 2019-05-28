/* Generated By:JavaCC: Do not edit this line. BParserVisitor.java Version 5.0 */
package b.parser;

public interface BParserVisitor {
    Object visit(SimpleNode node, java.util.Map<Object, Object> data);

    Object visit(ASTMachine node, java.util.Map<Object, Object> data);

    Object visit(ASTConstDef node, java.util.Map<Object, Object> data);

    Object visit(ASTConstDefs node, java.util.Map<Object, Object> data);

    Object visit(ASTSetDefs node, java.util.Map<Object, Object> data);

    Object visit(ASTSetDef node, java.util.Map<Object, Object> data);

    Object visit(ASTSetType node, java.util.Map<Object, Object> data);

    Object visit(ASTVarDefs node, java.util.Map<Object, Object> data);

    Object visit(ASTVarDef node, java.util.Map<Object, Object> data);

    Object visit(ASTFunDefs node, java.util.Map<Object, Object> data);

    Object visit(ASTFunDef node, java.util.Map<Object, Object> data);

    Object visit(ASTInvariant node, java.util.Map<Object, Object> data);

    Object visit(ASTInitialisation node, java.util.Map<Object, Object> data);

    Object visit(ASTEvents node, java.util.Map<Object, Object> data);

    Object visit(ASTEvent node, java.util.Map<Object, Object> data);

    Object visit(ASTSkip node, java.util.Map<Object, Object> data);

    Object visit(ASTVarAssignment node, java.util.Map<Object, Object> data);

    Object visit(ASTFunAssignment node, java.util.Map<Object, Object> data);

    Object visit(ASTSelect node, java.util.Map<Object, Object> data);

    Object visit(ASTIfThenElse node, java.util.Map<Object, Object> data);

    Object visit(ASTChoice node, java.util.Map<Object, Object> data);

    Object visit(ASTQuantifiedSymbolsDefs node, java.util.Map<Object, Object> data);

    Object visit(ASTAny node, java.util.Map<Object, Object> data);

    Object visit(ASTSequence node, java.util.Map<Object, Object> data);

    Object visit(ASTQuantifiedVarDef node, java.util.Map<Object, Object> data);

    Object visit(ASTQuantifiedFunDef node, java.util.Map<Object, Object> data);

    Object visit(ASTExpr node, java.util.Map<Object, Object> data);

    Object visit(ASTEquiv node, java.util.Map<Object, Object> data);

    Object visit(ASTImplies node, java.util.Map<Object, Object> data);

    Object visit(ASTOr node, java.util.Map<Object, Object> data);

    Object visit(ASTAnd node, java.util.Map<Object, Object> data);

    Object visit(ASTEq node, java.util.Map<Object, Object> data);

    Object visit(ASTNEq node, java.util.Map<Object, Object> data);

    Object visit(ASTIn node, java.util.Map<Object, Object> data);

    Object visit(ASTNotIn node, java.util.Map<Object, Object> data);

    Object visit(ASTLT node, java.util.Map<Object, Object> data);

    Object visit(ASTLE node, java.util.Map<Object, Object> data);

    Object visit(ASTGT node, java.util.Map<Object, Object> data);

    Object visit(ASTGE node, java.util.Map<Object, Object> data);

    Object visit(ASTPlus node, java.util.Map<Object, Object> data);

    Object visit(ASTMinus node, java.util.Map<Object, Object> data);

    Object visit(ASTTimes node, java.util.Map<Object, Object> data);

    Object visit(ASTDiv node, java.util.Map<Object, Object> data);

    Object visit(ASTMod node, java.util.Map<Object, Object> data);

    Object visit(ASTNot node, java.util.Map<Object, Object> data);

    Object visit(ASTUMinus node, java.util.Map<Object, Object> data);

    Object visit(ASTFunCall node, java.util.Map<Object, Object> data);

    Object visit(ASTFalse node, java.util.Map<Object, Object> data);

    Object visit(ASTTrue node, java.util.Map<Object, Object> data);

    Object visit(ASTDouble node, java.util.Map<Object, Object> data);

    Object visit(ASTInt node, java.util.Map<Object, Object> data);

    Object visit(ASTExists node, java.util.Map<Object, Object> data);

    Object visit(ASTForAll node, java.util.Map<Object, Object> data);

    Object visit(ASTString node, java.util.Map<Object, Object> data);

    Object visit(ASTIdentifier node, java.util.Map<Object, Object> data);

    Object visit(ASTSet node, java.util.Map<Object, Object> data);

    Object visit(ASTRange node, java.util.Map<Object, Object> data);

    Object visit(ASTArithType node, java.util.Map<Object, Object> data);

    Object visit(ASTBoolType node, java.util.Map<Object, Object> data);

    Object visit(ASTIntType node, java.util.Map<Object, Object> data);

    Object visit(ASTRealType node, java.util.Map<Object, Object> data);

    Object visit(ASTStringType node, java.util.Map<Object, Object> data);
}
/* JavaCC - OriginalChecksum=7f71bc5a7107f7bb62bf4c9994984fe9 (do not edit this line) */
