/* Generated By:JJTree: Do not edit this line. ASTQuantifiedVarDef.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package b.parser;

public
class ASTQuantifiedVarDef extends SimpleNode {
    public ASTQuantifiedVarDef(int id) {
        super(id);
    }

    public ASTQuantifiedVarDef(BParser p, int id) {
        super(p, id);
    }


    /**
     * Accept the visitor.
     **/
    public Object jjtAccept(BParserVisitor visitor, java.util.Map<Object, Object> data) {
        return visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=b5e68597903774933eb36015e396fafa (do not edit this line) */