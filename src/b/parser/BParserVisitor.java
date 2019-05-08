/* Generated By:JavaCC: Do not edit this line. BParserVisitor.java Version 5.0 */
package b.parser;

public interface BParserVisitor {
  Object visit(SimpleNode node, java.util.Map<Object, Object> data);
  Object visit(ASTMachine node, java.util.Map<Object, Object> data);

  Object visit(ASTConstDef node, java.util.Map<Object, Object> data);

  Object visit(ASTConstDefs node, java.util.Map<Object, Object> data);

  Object visit(ASTIdentifier node, java.util.Map<Object, Object> data);

  Object visit(ASTSetDef node, java.util.Map<Object, Object> data);

  Object visit(ASTSetDefs node, java.util.Map<Object, Object> data);

  Object visit(ASTVarDef node, java.util.Map<Object, Object> data);

  Object visit(ASTVarDefs node, java.util.Map<Object, Object> data);
  Object visit(ASTExpr node, java.util.Map<Object, Object> data);
  Object visit(ASTEquiv node, java.util.Map<Object, Object> data);
  Object visit(ASTImplies node, java.util.Map<Object, Object> data);
  Object visit(ASTOr node, java.util.Map<Object, Object> data);
  Object visit(ASTAnd node, java.util.Map<Object, Object> data);
  Object visit(ASTEq node, java.util.Map<Object, Object> data);
  Object visit(ASTNEq node, java.util.Map<Object, Object> data);
  Object visit(ASTIn node, java.util.Map<Object, Object> data);
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
  Object visit(ASTFalse node, java.util.Map<Object, Object> data);
  Object visit(ASTTrue node, java.util.Map<Object, Object> data);
  Object visit(ASTDouble node, java.util.Map<Object, Object> data);
  Object visit(ASTInt node, java.util.Map<Object, Object> data);
  Object visit(ASTEmptySet node, java.util.Map<Object, Object> data);
  Object visit(ASTSeq node, java.util.Map<Object, Object> data);
  Object visit(ASTSet node, java.util.Map<Object, Object> data);
}
/* JavaCC - OriginalChecksum=fdef7a67a8e43212ee0a02fb8c375bae (do not edit this line) */
