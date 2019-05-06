/* Generated By:JJTree: Do not edit this line. SimpleNode.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package b.parser;

public
class SimpleNode implements Node {

  protected Node parent;
  protected Node[] children;
  protected int id;
  protected Object value;
  protected BParser parser;
  private SourceCoordinates sourceCoordinates;

  public SimpleNode(int i) {
    id = i;
  }

  public SimpleNode(BParser p, int i) {
    this(i);
    parser = p;
  }

  public void jjtOpen() {
  }

  public void jjtClose() {
  }

  public void jjtSetParent(Node n) {
    parent = n;
  }

  public Node jjtGetParent() {
    return parent;
  }

  public void jjtAddChild(Node n, int i) {
    if (children == null) {
      children = new Node[i + 1];
    } else if (i >= children.length) {
      Node[] c = new Node[i + 1];
      System.arraycopy(children, 0, c, 0, children.length);
      children = c;
    }
    children[i] = n;
  }

  public Node jjtGetChild(int i) {
    return children[i];
  }

  public int jjtGetNumChildren() {
    return (children == null) ? 0 : children.length;
  }

  public void jjtSetValue(Object value) {
    this.value = value;
  }

  public Object jjtGetValue() {
    return value;
  }

  /**
   * Accept the visitor.
   **/
  public Object jjtAccept(BParserVisitor visitor, java.util.Map<Object, Object> data) {
    return visitor.visit(this, data);
  }

  /**
   * Accept the visitor.
   **/
  public Object childrenAccept(BParserVisitor visitor, java.util.Map<Object, Object> data) {
    if (children != null) {
      for (Node child : children) {
        child.jjtAccept(visitor, data);
      }
    }
    return data;
  }

  /* You can override these two methods in subclasses of SimpleNode to
     customize the way the node appears when the tree is dumped.  If
     your output uses more than one line you should override
     toString(String), otherwise overriding toString() is probably all
     you need to do. */

  public Node[] getChildren() {
    return children;
  }

  public SourceCoordinates getSourceCoordinates() {
    return sourceCoordinates;
  }

  public void setSourceCoordinates(SourceCoordinates sourceCoordinates) {
    this.sourceCoordinates = sourceCoordinates;
  }

  public String toString() {
    return BParserTreeConstants.jjtNodeName[id] + (jjtGetValue() == null ? "" : "[" + jjtGetValue() + "]") + " - l." + getSourceCoordinates().getLineStart() + " -> " + getSourceCoordinates().getLineEnd() + ", c." + getSourceCoordinates().getColumnStart() + " -> " + getSourceCoordinates().getColumnEnd();
  }

  public String toString(String prefix) {
    return prefix + toString(); }

  /* Override this method if you want to customize how the node dumps
     out its children. */

  public void dump(String prefix) {
    System.out.println(toString(prefix));
    if (children != null) {
      for (Node child : children) {
        SimpleNode n = (SimpleNode) child;
        if (n != null) {
          n.dump(prefix + " ");
        }
      }
    }
  }
}

/* JavaCC - OriginalChecksum=55babde73452cd8f05cf3cf75727a1ae (do not edit this line) */
