package b.lang.bobjectvisitors.primer;

import b.lang.exprs.arith.*;
import b.lang.exprs.bool.False;
import b.lang.exprs.bool.True;
import b.lang.exprs.string.StringVal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Created by gvoiron on 30/05/19.
 * Time : 02:57
 */
class ExprPrimerTest {

    @Test
    public void visitPlus_ok() {
        assertEquals(new Plus(new Int(0), new IntVar("v0" + ExprPrimer.getPrimeSuffix()), new Plus(new Real(42.3), new RealVar("v1" + ExprPrimer.getPrimeSuffix()))), new Plus(new Int(0), new IntVar("v0"), new Plus(new Real(42.3), new RealVar("v1"))).accept(new ExprPrimer()));
    }

    @Test
    public void visitInt_ok() {
        assertEquals(new Int(42), new Int(42).accept(new ExprPrimer()));
    }

    @Test
    public void visitMinus_ok() {
        assertEquals(new Minus(new Int(0), new IntVar("v0" + ExprPrimer.getPrimeSuffix()), new Minus(new Real(42.3), new RealVar("v1" + ExprPrimer.getPrimeSuffix()))), new Minus(new Int(0), new IntVar("v0"), new Plus(new Real(42.3), new RealVar("v1"))).accept(new ExprPrimer()));
    }

    @Test
    public void visitTimes_ok() {
        assertEquals(new Times(new Int(0), new IntVar("v0" + ExprPrimer.getPrimeSuffix()), new Times(new Real(42.3), new RealVar("v1" + ExprPrimer.getPrimeSuffix()))), new Times(new Int(0), new IntVar("v0"), new Times(new Real(42.3), new RealVar("v1"))).accept(new ExprPrimer()));
    }

    @Test
    public void visitDiv_ok() {
        assertEquals(new Div(new Int(0), new IntVar("v0" + ExprPrimer.getPrimeSuffix()), new Div(new Real(42.3), new RealVar("v1" + ExprPrimer.getPrimeSuffix()))), new Div(new Int(0), new IntVar("v0"), new Div(new Real(42.3), new RealVar("v1"))).accept(new ExprPrimer()));
    }

    @Test
    public void visitMod_ok() {
        assertEquals(new Mod(new Int(0), new IntVar("v0" + ExprPrimer.getPrimeSuffix()), new Mod(new Real(42.3), new RealVar("v1" + ExprPrimer.getPrimeSuffix()))), new Mod(new Int(0), new IntVar("v0"), new Mod(new Real(42.3), new RealVar("v1"))).accept(new ExprPrimer()));
    }

    @Test
    public void visitFalse_ok() {
        assertEquals(new False(), new False().accept(new ExprPrimer()));
    }

    @Test
    public void visitTrue_ok() {
        assertEquals(new True(), new True().accept(new ExprPrimer()));
    }

    @Test
    public void visitReal_ok() {
        assertEquals(new Real(42.3), new Real(42.3).accept(new ExprPrimer()));
    }

    @Test
    public void visitRange_ok() {
        fail();
    }

    @Test
    public void visitSet_ok() {
        fail();
    }

    @Test
    public void visitStringVal_ok() {
        assertEquals(new StringVal("value"), new StringVal("value").accept(new ExprPrimer()));
    }

    @Test
    public void visitEq_ok() {
    }

    @Test
    public void visitInvariant_ok() {

    }

    @Test
    public void visitAnd_ok() {

    }

    @Test
    public void visitOr_ok() {

    }

    @Test
    public void visitRealConst_ok() {

    }

    @Test
    public void visitSetConst_ok() {

    }

    @Test
    public void visitBoolVar_ok() {

    }

    @Test
    public void visitIntConst_ok() {

    }

    @Test
    public void visitStringConst_ok() {

    }

    @Test
    public void visitSetVar_ok() {

    }

    @Test
    public void visitIntVar_ok() {

    }

    @Test
    public void visitRealVar_ok() {

    }

    @Test
    public void visitStringVar_ok() {

    }

    @Test
    public void visitBoolConst_ok() {

    }

    @Test
    public void visitFunCall_ok() {

    }

}