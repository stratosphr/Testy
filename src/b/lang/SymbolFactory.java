package b.lang;

import b.lang.exprs.AConst;
import b.lang.exprs.IExpr;
import b.lang.exprs.arith.IArithExpr;
import b.lang.exprs.arith.IntConst;
import b.lang.exprs.arith.RealConst;
import b.lang.exprs.bool.BoolConst;
import b.lang.exprs.bool.IBoolExpr;
import b.lang.exprs.bool.SetConst;
import b.lang.exprs.set.ISetExpr;
import b.lang.exprs.string.IStringExpr;
import b.lang.exprs.string.StringConst;
import b.lang.types.AType;
import b.lang.types.SetType;

import static b.lang.types.Types.*;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 17:25
 */
public final class SymbolFactory {

    public static AConst buildConst(AType type, String name, IExpr value) {
        if (type.equals(getIntType())) {
            return new IntConst(name, (IArithExpr) value);
        } else if (type.equals(getRealType())) {
            return new RealConst(name, (IArithExpr) value);
        } else if (type.equals(getBoolType())) {
            return new BoolConst(name, (IBoolExpr) value);
        } else if (type.instanceOf(getSetType(getObjectType()))) {
            return new SetConst(name, (ISetExpr) value);
        } else if (type.equals(getStringType())) {
            return new StringConst(name, (IStringExpr) value);
        } else {
            return null;
        }
    }

    public static ASymbol buildSet(SetType type, String name, ISetExpr value) {
        return new SetConst(name, value);
    }

    public static ASymbol buildVar(AType type, String name) {
        return null;
    }

    public static ASymbol buildFunCall(AType type, String name) {
        return null;
    }

}
