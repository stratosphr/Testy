/* Generated By:JJTree: Do not edit this line. ASTIn.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package b.parser;

public
class ASTIn extends SimpleNode {
    public ASTIn(int id) {
        super(id);
    }

    public ASTIn(BParser p, int id) {
        super(p, id);
    }


    /**
     * Accept the visitor.
     **/
    public Object jjtAccept(BParserVisitor visitor, java.util.Map<Object, Object> data) {
        return visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=a4512495bd99217cb2eff24f6cdd1ea0 (do not edit this line) */
