package b.lang.bobjectvisitors.primer;

import b.lang.exprs.FunCall;
import b.lang.exprs.FunSymbol;
import b.lang.exprs.arith.*;
import b.lang.exprs.bool.*;
import b.lang.exprs.set.Range;
import b.lang.exprs.set.Set;
import b.lang.exprs.set.SetConst;
import b.lang.exprs.set.SetVar;
import b.lang.exprs.string.StringConst;
import b.lang.exprs.string.StringVal;
import b.lang.exprs.string.StringVar;

/**
 * Created by gvoiron on 30/05/19.
 * Time : 02:51
 */
public final class ExprPrimer implements IExprPrimer {

    public static String getPrimeSuffix() {
        return "_";
    }

    @Override
    public Plus visit(Plus plus) {
        //return new Plus(plus.getOperands().stream().map(expr -> (IArithExpr) expr.accept(this)).toArray(IArithExpr[]::new));
        return null;
    }

    @Override
    public ANumber<Integer> visit(Int anInt) {
        //return new Int(anInt.getValue());
        return null;
    }

    @Override
    public Minus visit(Minus minus) {
        return null;
    }

    @Override
    public Times visit(Times times) {
        return null;
    }

    @Override
    public Div visit(Div div) {
        return null;
    }

    @Override
    public Mod visit(Mod mod) {
        return null;
    }

    @Override
    public False visit(False aFalse) {
        return null;
    }

    @Override
    public True visit(True aTrue) {
        return null;
    }

    @Override
    public ANumber<Double> visit(Real real) {
        //return new Real(real.getValue());
        return null;
    }

    @Override
    public Range visit(Range range) {
        return null;
    }

    @Override
    public Set visit(Set set) {
        return null;
    }

    @Override
    public StringVal visit(StringVal stringVal) {
        return null;
    }

    @Override
    public Eq visit(Eq eq) {
        return null;
    }

    @Override
    public Invariant visit(Invariant invariant) {
        return null;
    }

    @Override
    public And visit(And and) {
        return null;
    }

    @Override
    public Or visit(Or or) {
        return null;
    }

    @Override
    public RealConst visit(RealConst realConst) {
        return null;
    }

    @Override
    public SetConst visit(SetConst setConst) {
        return null;
    }

    @Override
    public BoolVar visit(BoolVar boolVar) {
        return null;
    }

    @Override
    public IntConst visit(IntConst intConst) {
        return null;
    }

    @Override
    public StringConst visit(StringConst stringConst) {
        return null;
    }

    @Override
    public SetVar visit(SetVar setVar) {
        return null;
    }

    @Override
    public IntVar visit(IntVar intVar) {
        return null;
    }

    @Override
    public RealVar visit(RealVar realVar) {
        return null;
    }

    @Override
    public StringVar visit(StringVar stringVar) {
        return null;
    }

    @Override
    public FunSymbol visit(FunSymbol funSymbol) {
        return null;
    }

    @Override
    public BoolConst visit(BoolConst boolConst) {
        return null;
    }

    @Override
    public FunCall visit(FunCall funCall) {
        return null;
    }

}
