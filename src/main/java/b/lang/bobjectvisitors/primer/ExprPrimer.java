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

    public static final String PRIME_SUFFIX = "_";
    private boolean isVisitingInvariant;

    public ExprPrimer() {
        this.isVisitingInvariant = false;
    }

    @Override
    public FunCall visit(FunCall funCall) {
        return isVisitingInvariant ? new FunCall(funCall.getName() + PRIME_SUFFIX, funCall.getOperand().accept(this)) : new FunCall(funCall.getName() + PRIME_SUFFIX, funCall.getOperand());
    }

    @Override
    public FunSymbol visit(FunSymbol funSymbol) {
        return null;
    }

    @Override
    public Int visit(Int anInt) {
        return new Int(anInt.getValue());
    }

    @Override
    public IntConst visit(IntConst intConst) {
        return new IntConst(intConst.getName(), intConst.getValue());
    }

    @Override
    public IntVar visit(IntVar intVar) {
        return new IntVar(intVar.getName() + PRIME_SUFFIX);
    }

    @Override
    public Real visit(Real real) {
        return new Real(real.getValue());
    }

    @Override
    public RealConst visit(RealConst realConst) {
        return new RealConst(realConst.getName(), realConst.getValue());
    }

    @Override
    public RealVar visit(RealVar realVar) {
        return new RealVar(realVar.getName() + PRIME_SUFFIX);
    }

    @Override
    public Plus visit(Plus plus) {
        return new Plus(plus.getOperands().stream().map(expr -> (IArithExpr) expr.accept(this)).toArray(IArithExpr[]::new));
    }

    @Override
    public Minus visit(Minus minus) {
        return new Minus(minus.getOperands().stream().map(expr -> (IArithExpr) expr.accept(this)).toArray(IArithExpr[]::new));
    }

    @Override
    public Times visit(Times times) {
        return new Times(times.getOperands().stream().map(expr -> (IArithExpr) expr.accept(this)).toArray(IArithExpr[]::new));
    }

    @Override
    public Div visit(Div div) {
        return new Div(div.getOperands().stream().map(expr -> (IArithExpr) expr.accept(this)).toArray(IArithExpr[]::new));
    }

    @Override
    public Mod visit(Mod mod) {
        return new Mod(mod.getOperands().stream().map(expr -> (IArithExpr) expr.accept(this)).toArray(IArithExpr[]::new));
    }

    @Override
    public BoolConst visit(BoolConst boolConst) {
        return new BoolConst(boolConst.getName(), boolConst.getValue());
    }

    @Override
    public BoolVar visit(BoolVar boolVar) {
        return new BoolVar(boolVar.getName() + PRIME_SUFFIX);
    }

    @Override
    public Invariant visit(Invariant invariant) {
        isVisitingInvariant = true;
        Invariant primedInvariant = new Invariant(invariant.getExpr().accept(this));
        isVisitingInvariant = false;
        return primedInvariant;
    }

    @Override
    public False visit(False aFalse) {
        return new False();
    }

    @Override
    public True visit(True aTrue) {
        return new True();
    }

    @Override
    public And visit(And and) {
        return new And(and.getOperands().stream().map(expr -> (IBoolExpr) expr.accept(this)).toArray(IBoolExpr[]::new));
    }

    @Override
    public Or visit(Or or) {
        return new Or(or.getOperands().stream().map(expr -> (IBoolExpr) expr.accept(this)).toArray(IBoolExpr[]::new));
    }

    @Override
    public Eq visit(Eq eq) {
        return new Eq(eq.getLeft().accept(this), eq.getRight().accept(this));
    }

    @Override
    public Range visit(Range range) {
        return new Range(range.getLowerBound(), range.getUpperBound());
    }

    @Override
    public Set visit(Set set) {
        return new Set(set.getElements());
    }

    @Override
    public SetConst visit(SetConst setConst) {
        return new SetConst(setConst.getName(), setConst.getValue());
    }

    @Override
    public SetVar visit(SetVar setVar) {
        return new SetVar(setVar.getName() + PRIME_SUFFIX);
    }

    @Override
    public StringVal visit(StringVal stringVal) {
        return new StringVal(stringVal.getValue());
    }

    @Override
    public StringConst visit(StringConst stringConst) {
        return new StringConst(stringConst.getName(), stringConst.getValue());
    }

    @Override
    public StringVar visit(StringVar stringVar) {
        return new StringVar(stringVar.getName() + PRIME_SUFFIX);
    }

}
