/* Generated By:JJTree&JavaCC: Do not edit this line. BParser.java */
package b.parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("ALL")
public class BParser/*@bgen(jjtree)*/ implements BParserTreeConstants, BParserConstants {/*@bgen(jjtree)*/
  protected static JJTBParserState jjtree = new JJTBParserState();
    static final private int[] jj_la1 = new int[5];
    /**
     * Generated Token Manager.
     */
    static public BParserTokenManager token_source;
    /**
     * Current token.
     */
    static public Token token;
    /**
     * Next token.
     */
    static public Token jj_nt;
  static SimpleCharStream jj_input_stream;
    private static BParser singleton;
  static private boolean jj_initialized_once = false;
  static private int jj_ntk;
  static private int jj_gen;
  static private int[] jj_la1_0;
  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

    static {
        jj_la1_init_0();
    }

    /** Constructor with InputStream. */
  public BParser(java.io.InputStream stream) {
      this(stream, null);
  }

    /** Constructor with InputStream and supplied encoding */
  public BParser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
      jj_initialized_once = true;
      try {
          jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1);
      } catch (java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new BParserTokenManager(jj_input_stream);
    token = new Token();
      jj_ntk = -1;
      jj_gen = 0;
      for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }
  /** Constructor. */
  public BParser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new BParserTokenManager(jj_input_stream);
    token = new Token();
      jj_ntk = -1;
      jj_gen = 0;
      for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

    /** Constructor with generated Token Manager. */
  public BParser(BParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
      jj_ntk = -1;
      jj_gen = 0;
      for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

    public static void setInputFile(String file) throws ParseException, FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        if (singleton == null) {
            singleton = new BParser(bufferedReader);
        } else {
            BParser.ReInit(bufferedReader);
        }
    }

  static final public SimpleNode parseMachine() throws ParseException {
      /*@bgen(jjtree) MachineAST */
    SimpleNode jjtn000 = new SimpleNode(JJTMACHINEAST);
      boolean jjtc000 = true;
      jjtree.openNodeScope(jjtn000);Token name;
    try {
      jj_consume_token(MACHINE);
      name = jj_consume_token(IDENTIFIER);
        jjtree.closeNodeScope(jjtn000, true);
        jjtc000 = false;
        jjtn000.value = name.image;
        {if (true) return jjtn000;}
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
    throw new Error("Missing return statement in function");
  }

  static final public SimpleNode parseExpr() throws ParseException {
      /*@bgen(jjtree) ExprAST */
      SimpleNode jjtn000 = new SimpleNode(JJTEXPRAST);
      boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      parseOperators1();
        label_1:
        while (true) {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case MINUS:
                case PLUS:
                    ;
                    break;
                default:
                    jj_la1[0] = jj_gen;
                    break label_1;
            }
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case PLUS:
                    jj_consume_token(PLUS);
                    SimpleNode jjtn001 = new SimpleNode(JJTPLUSAST);
                    boolean jjtc001 = true;
                    jjtree.openNodeScope(jjtn001);
                    try {
                        parseOperators1();
                    } catch (Throwable jjte001) {
                        if (jjtc001) {
                            jjtree.clearNodeScope(jjtn001);
                            jjtc001 = false;
                        } else {
                            jjtree.popNode();
                        }
                        if (jjte001 instanceof RuntimeException) {
                            {
                                if (true) throw (RuntimeException) jjte001;
                            }
                        }
                        if (jjte001 instanceof ParseException) {
                            {
                                if (true) throw (ParseException) jjte001;
                            }
                        }
                        {
                            if (true) throw (Error) jjte001;
                        }
                    } finally {
                        if (jjtc001) {
                            jjtree.closeNodeScope(jjtn001, 2);
                        }
                    }
                    break;
                case MINUS:
                    jj_consume_token(MINUS);
                    SimpleNode jjtn002 = new SimpleNode(JJTMINUSAST);
                    boolean jjtc002 = true;
                    jjtree.openNodeScope(jjtn002);
                    try {
                        parseOperators1();
                    } catch (Throwable jjte002) {
                        if (jjtc002) {
                            jjtree.clearNodeScope(jjtn002);
                            jjtc002 = false;
                        } else {
                            jjtree.popNode();
                        }
                        if (jjte002 instanceof RuntimeException) {
                            {
                                if (true) throw (RuntimeException) jjte002;
                            }
                        }
                        if (jjte002 instanceof ParseException) {
                            {
                                if (true) throw (ParseException) jjte002;
                            }
                        }
                        {
                            if (true) throw (Error) jjte002;
                        }
                    } finally {
                        if (jjtc002) {
                            jjtree.closeNodeScope(jjtn002, 2);
                        }
                    }
                    break;
                default:
                    jj_la1[1] = jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
            }
        }
        jjtree.closeNodeScope(jjtn000, true);
        jjtc000 = false;
        {if (true) return jjtn000;}
    } catch (Throwable jjte000) {
      if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
          {
              if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
          {
              if (true) throw (ParseException) jjte000;
          }
      }
        {if (true) throw (Error)jjte000;}
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
    throw new Error("Missing return statement in function");
  }

    static final public void parseOperators1() throws ParseException {
        parseTerminal();
        label_2:
        while (true) {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case TIMES:
                case DIV:
                case MOD:
                    ;
                    break;
                default:
                    jj_la1[2] = jj_gen;
                    break label_2;
            }
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case TIMES:
                    jj_consume_token(TIMES);
                    SimpleNode jjtn001 = new SimpleNode(JJTTIMESAST);
                    boolean jjtc001 = true;
                    jjtree.openNodeScope(jjtn001);
                    try {
                        parseTerminal();
                    } catch (Throwable jjte001) {
                        if (jjtc001) {
                            jjtree.clearNodeScope(jjtn001);
                            jjtc001 = false;
                        } else {
                            jjtree.popNode();
                        }
                        if (jjte001 instanceof RuntimeException) {
                            {
                                if (true) throw (RuntimeException) jjte001;
                            }
                        }
                        if (jjte001 instanceof ParseException) {
                            {
                                if (true) throw (ParseException) jjte001;
                            }
                        }
                        {
                            if (true) throw (Error) jjte001;
                        }
                    } finally {
                        if (jjtc001) {
                            jjtree.closeNodeScope(jjtn001, 2);
                        }
                    }
                    break;
                case DIV:
                    jj_consume_token(DIV);
                    SimpleNode jjtn002 = new SimpleNode(JJTDIVAST);
                    boolean jjtc002 = true;
                    jjtree.openNodeScope(jjtn002);
                    try {
                        parseTerminal();
                    } catch (Throwable jjte002) {
                        if (jjtc002) {
                            jjtree.clearNodeScope(jjtn002);
                            jjtc002 = false;
                        } else {
                            jjtree.popNode();
                        }
                        if (jjte002 instanceof RuntimeException) {
                            {
                                if (true) throw (RuntimeException) jjte002;
                            }
                        }
                        if (jjte002 instanceof ParseException) {
                            {
                                if (true) throw (ParseException) jjte002;
                            }
                        }
                        {
                            if (true) throw (Error) jjte002;
                        }
                    } finally {
                        if (jjtc002) {
                            jjtree.closeNodeScope(jjtn002, 2);
                        }
                    }
                    break;
                case MOD:
                    jj_consume_token(MOD);
                    SimpleNode jjtn003 = new SimpleNode(JJTMODAST);
                    boolean jjtc003 = true;
                    jjtree.openNodeScope(jjtn003);
                    try {
                        parseTerminal();
                    } catch (Throwable jjte003) {
                        if (jjtc003) {
                            jjtree.clearNodeScope(jjtn003);
                            jjtc003 = false;
                        } else {
                            jjtree.popNode();
                        }
                        if (jjte003 instanceof RuntimeException) {
                            {
                                if (true) throw (RuntimeException) jjte003;
                            }
                        }
                        if (jjte003 instanceof ParseException) {
                            {
                                if (true) throw (ParseException) jjte003;
                            }
                        }
                        {
                            if (true) throw (Error) jjte003;
                        }
                    } finally {
                        if (jjtc003) {
                            jjtree.closeNodeScope(jjtn003, 2);
                        }
                    }
                    break;
                default:
                    jj_la1[3] = jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
            }
        }
    }

    static final public void parseTerminal() throws ParseException {
        Token t;
        switch ((jj_ntk == -1) ? jj_ntk():jj_ntk) {
    case IDENTIFIER:
        t = jj_consume_token(IDENTIFIER);
        SimpleNode jjtn001 = new SimpleNode(JJTIDENTIFIERAST);
        boolean jjtc001 = true;
        jjtree.openNodeScope(jjtn001);
        try {
            jjtree.closeNodeScope(jjtn001, 0);
            jjtc001 = false;
            jjtn001.value = t.image;
        } finally {
            if (jjtc001) {
                jjtree.closeNodeScope(jjtn001, 0);
            }
        }
        break;
            case NUMBER:
                t = jj_consume_token(NUMBER);
                SimpleNode jjtn002 = new SimpleNode(JJTNUMBERAST);
                boolean jjtc002 = true;
                jjtree.openNodeScope(jjtn002);
                try {
                    jjtree.closeNodeScope(jjtn002, 0);
                    jjtc002 = false;
                    jjtn002.value = t.image;
                } finally {
                    if (jjtc002) {
                        jjtree.closeNodeScope(jjtn002, 0);
                    }
                }
                break;
            case 15:
                jj_consume_token(15);
                parseExpr();
                jj_consume_token(16);
                break;
            default:
                jj_la1[4] = jj_gen;
                jj_consume_token(-1);
      throw new ParseException();
        }
    }

    private static void jj_la1_init_0() {
        jj_la1_0 = new int[]{0xc0, 0xc0, 0x700, 0x700, 0xc800,};
   }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
      ReInit(stream, null);
  }

    static private Token jj_consume_token(int kind) throws ParseException {
        Token oldToken;
        if ((oldToken = token).next != null) token = token.next;
        else token = token.next = token_source.getNextToken();
        jj_ntk = -1;
        if (token.kind == kind) {
            jj_gen++;
            return token;
        }
        token = oldToken;
        jj_kind = kind;
        throw generateParseException();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
      try {
          jj_input_stream.ReInit(stream, encoding, 1, 1);
      } catch (java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
      jjtree.reset();
      jj_gen = 0;
      for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
      jjtree.reset();
      jj_gen = 0;
      for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
      jj_gen++;
      return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

    static private int jj_ntk() {
        if ((jj_nt = token.next) == null)
            return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
            return (jj_ntk = jj_nt.kind);
    }

  /** Generate ParseException. */
  static public ParseException generateParseException() {
      jj_expentries.clear();
      boolean[] la1tokens = new boolean[17];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
        jj_kind = -1;
    }
    for (int i = 0; i < 5; i++) {
      if (jj_la1[i] == jj_gen) {
          for (int j = 0; j < 32; j++) {
              if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
              }
          }
      }
    }
    for (int i = 0; i < 17; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

  /** Reinitialise. */
  public void ReInit(BParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
      jjtree.reset();
      jj_gen = 0;
      for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

}
