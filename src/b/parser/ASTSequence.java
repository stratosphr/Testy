/* Generated By:JJTree: Do not edit this line. ASTSequence.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package b.parser;

public
class ASTSequence extends SimpleNode {
    public ASTSequence(int id) {
        super(id);
    }

    public ASTSequence(BParser p, int id) {
        super(p, id);
    }


    /**
     * Accept the visitor.
     **/
    public Object jjtAccept(BParserVisitor visitor, java.util.Map<Object, Object> data) {
        return visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=a0452be3609f34139a1e24787f90c67a (do not edit this line) */