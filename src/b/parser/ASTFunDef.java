/* Generated By:JJTree: Do not edit this line. ASTFunDef.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package b.parser;

public
class ASTFunDef extends SimpleNode {
    public ASTFunDef(int id) {
        super(id);
    }

    public ASTFunDef(BParser p, int id) {
        super(p, id);
    }


    /**
     * Accept the visitor.
     **/
    public Object jjtAccept(BParserVisitor visitor, java.util.Map<Object, Object> data) {
        return visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=8aa8312e2e299e94bde519b944a39ae2 (do not edit this line) */
