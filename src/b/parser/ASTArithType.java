/* Generated By:JJTree: Do not edit this line. ASTArithType.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package b.parser;

public
class ASTArithType extends SimpleNode {
    public ASTArithType(int id) {
        super(id);
    }

    public ASTArithType(BParser p, int id) {
        super(p, id);
    }


    /**
     * Accept the visitor.
     **/
    public Object jjtAccept(BParserVisitor visitor, java.util.Map<Object, Object> data) {
        return visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=2b2ba960d6fb8519f7650ebaeb7b4c72 (do not edit this line) */
