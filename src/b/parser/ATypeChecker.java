package b.parser;

import b.lang.ABObject;
import b.lang.exprs.Symbol;
import b.lang.exprs.arith.AArithExpr;
import b.lang.exprs.arith.ArithVar;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gvoiron on 04/05/19.
 * Time : 23:36
 */
public abstract class ATypeChecker {

    private Map<String, ABObject> symbolsTable;

    public ATypeChecker() {
        this.symbolsTable = new HashMap<>();
    }

    protected Object getSymbolOrRegister(String symbol) {
        return symbolsTable.containsKey(symbol) ? symbolsTable.get(symbol) : new Symbol(symbol);
    }

    protected AArithExpr checkArith(Object bObject, SimpleNode node, Map<Object, Object> data) {
        AArithExpr expr = null;
        if (bObject instanceof AArithExpr) {
            expr = (AArithExpr) bObject;
        } else if (bObject instanceof Symbol) {
            if (symbolsTable.containsKey(((Symbol) bObject).getName())) {
                if (symbolsTable.get(((Symbol) bObject).getName()) instanceof AArithExpr) {
                    expr = (AArithExpr) symbolsTable.get(((Symbol) bObject).getName());
                }
            } else {
                expr = new ArithVar(((Symbol) bObject).getName());
                symbolsTable.put(((Symbol) bObject).getName(), expr);
            }
        }
        if (expr == null) {
            try {
                throw new Exception("Expected arithmetic expression but " + (node instanceof ASTExpr ? node.jjtGetChild(0) : node.toString()) + " encountered.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return expr;
    }

}
