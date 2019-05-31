package b.lang.bobjectvisitors.primer;

import b.lang.exprs.FunCall;
import b.lang.exprs.arith.*;
import b.lang.exprs.bool.*;
import b.lang.exprs.set.Range;
import b.lang.exprs.set.Set;
import b.lang.exprs.set.SetConst;
import b.lang.exprs.set.SetVar;
import b.lang.exprs.string.StringConst;
import b.lang.exprs.string.StringVal;
import b.lang.exprs.string.StringVar;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;

import static b.lang.bobjectvisitors.primer.ExprPrimer.PRIME_SUFFIX;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Created by gvoiron on 30/05/19.
 * Time : 02:57
 */
class ExprPrimerTest {

    @Test
    public void visitPlus_ok() {
        assertEquals(new Plus(new Int(0), new IntVar("v0" + PRIME_SUFFIX), new Plus(new Real(42.3), new RealVar("v1" + PRIME_SUFFIX))), new Plus(new Int(0), new IntVar("v0"), new Plus(new Real(42.3), new RealVar("v1"))).accept(new ExprPrimer()));
    }

    @Test
    public void visitInt_ok() {
        assertEquals(new Int(42), new Int(42).accept(new ExprPrimer()));
    }

    @Test
    public void visitMinus_ok() {
        assertEquals(new Minus(new Int(0), new IntVar("v0" + PRIME_SUFFIX), new Minus(new Real(42.3), new RealVar("v1" + PRIME_SUFFIX))), new Minus(new Int(0), new IntVar("v0"), new Minus(new Real(42.3), new RealVar("v1"))).accept(new ExprPrimer()));
    }

    @Test
    public void visitTimes_ok() {
        assertEquals(new Times(new Int(0), new IntVar("v0" + PRIME_SUFFIX), new Times(new Real(42.3), new RealVar("v1" + PRIME_SUFFIX))), new Times(new Int(0), new IntVar("v0"), new Times(new Real(42.3), new RealVar("v1"))).accept(new ExprPrimer()));
    }

    @Test
    public void visitDiv_ok() {
        assertEquals(new Div(new Int(0), new IntVar("v0" + PRIME_SUFFIX), new Div(new Real(42.3), new RealVar("v1" + PRIME_SUFFIX))), new Div(new Int(0), new IntVar("v0"), new Div(new Real(42.3), new RealVar("v1"))).accept(new ExprPrimer()));
    }

    @Test
    public void visitMod_ok() {
        assertEquals(new Mod(new Int(0), new IntVar("v0" + PRIME_SUFFIX), new Mod(new Real(42.3), new RealVar("v1" + PRIME_SUFFIX))), new Mod(new Int(0), new IntVar("v0"), new Mod(new Real(42.3), new RealVar("v1"))).accept(new ExprPrimer()));
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
        assertEquals(new Range(new IntVar("v0"), new IntConst("v1", new Int(10))), new Range(new IntVar("v0"), new IntConst("v1", new Int(10))).accept(new ExprPrimer()));
    }

    @Test
    public void visitSet_ok() {
        assertEquals(new Set(new LinkedHashSet<>(Arrays.asList(new IntVar("v0"), new IntConst("v1", new Int(10)), new Mod(new Int(0), new IntVar("v0"), new Mod(new Real(42.3), new RealVar("v1")))))), new Set(new LinkedHashSet<>(Arrays.asList(new IntVar("v0"), new IntConst("v1", new Int(10)), new Mod(new Int(0), new IntVar("v0"), new Mod(new Real(42.3), new RealVar("v1")))))).accept(new ExprPrimer()));
    }

    @Test
    public void visitStringVal_ok() {
        assertEquals(new StringVal("value"), new StringVal("value").accept(new ExprPrimer()));
    }

    @Test
    public void visitEq_ok() {
        assertEquals(
                new Eq(
                        new IntVar("v0" + PRIME_SUFFIX),
                        new Mod(
                                new Int(0),
                                new IntVar("v0" + PRIME_SUFFIX),
                                new Mod(
                                        new Real(42.3),
                                        new RealVar("v1" + PRIME_SUFFIX)
                                )
                        )
                ),
                new Eq(
                        new IntVar("v0"),
                        new Mod(
                                new Int(0),
                                new IntVar("v0"),
                                new Mod(
                                        new Real(42.3),
                                        new RealVar("v1")
                                )
                        )
                ).accept(new ExprPrimer())
        );
    }

    @Test
    public void visitInvariant_ok() {
        assertEquals(
                new Invariant(
                        new Or(new Eq(new IntVar("v0" + PRIME_SUFFIX), new FunCall("f0" + PRIME_SUFFIX, new RealVar("v0" + PRIME_SUFFIX))), new Eq(new RealVar("v2" + PRIME_SUFFIX), new FunCall("f1" + PRIME_SUFFIX, new IntVar("v3" + PRIME_SUFFIX))))
                ),
                new Invariant(
                        new Or(new Eq(new IntVar("v0"), new FunCall("f0", new RealVar("v0"))), new Eq(new RealVar("v2"), new FunCall("f1", new IntVar("v3"))))
                ).accept(new ExprPrimer())
        );
    }

    @Test
    public void visitAnd_ok() {
        assertEquals(
                new And(
                        new Eq(
                                new Minus(new Int(0), new IntVar("v0" + PRIME_SUFFIX), new Minus(new Real(42.3), new RealVar("v1" + PRIME_SUFFIX))),
                                new Plus(new Int(0), new IntVar("v0" + PRIME_SUFFIX), new Plus(new Real(42.3), new RealVar("v1" + PRIME_SUFFIX)))
                        ),
                        new Eq(
                                new IntConst("c0", new Int(10)),
                                new Times(new Int(0), new IntVar("v0" + PRIME_SUFFIX), new Times(new Real(42.3), new RealVar("v1" + PRIME_SUFFIX)))
                        ),
                        new Eq(
                                new BoolConst("c1", new And(new False(), new BoolVar("v2"))),
                                new Div(new Int(0), new IntVar("v0" + PRIME_SUFFIX), new Div(new Real(42.3), new RealVar("v1" + PRIME_SUFFIX)))
                        )
                ),
                new And(
                        new Eq(
                                new Minus(new Int(0), new IntVar("v0"), new Minus(new Real(42.3), new RealVar("v1"))),
                                new Plus(new Int(0), new IntVar("v0"), new Plus(new Real(42.3), new RealVar("v1")))
                        ),
                        new Eq(
                                new IntConst("c0", new Int(10)),
                                new Times(new Int(0), new IntVar("v0"), new Times(new Real(42.3), new RealVar("v1")))
                        ),
                        new Eq(
                                new BoolConst("c1", new And(new False(), new BoolVar("v2"))),
                                new Div(new Int(0), new IntVar("v0"), new Div(new Real(42.3), new RealVar("v1")))
                        )
                ).accept(new ExprPrimer())
        );
    }

    @Test
    public void visitOr_ok() {
        assertEquals(
                new Or(
                        new Eq(
                                new Minus(new Int(0), new IntVar("v0" + PRIME_SUFFIX), new Minus(new Real(42.3), new RealVar("v1" + PRIME_SUFFIX))),
                                new Plus(new Int(0), new IntVar("v0" + PRIME_SUFFIX), new Plus(new Real(42.3), new RealVar("v1" + PRIME_SUFFIX)))
                        ),
                        new Eq(
                                new IntConst("c0", new Int(10)),
                                new Times(new Int(0), new IntVar("v0" + PRIME_SUFFIX), new Times(new Real(42.3), new RealVar("v1" + PRIME_SUFFIX)))
                        ),
                        new Eq(
                                new BoolConst("c1", new Or(new False(), new BoolVar("v2" + PRIME_SUFFIX))),
                                new Div(new Int(0), new IntVar("v0" + PRIME_SUFFIX), new Div(new Real(42.3), new RealVar("v1" + PRIME_SUFFIX)))
                        )
                ),
                new Or(
                        new Eq(
                                new Minus(new Int(0), new IntVar("v0"), new Minus(new Real(42.3), new RealVar("v1"))),
                                new Plus(new Int(0), new IntVar("v0"), new Plus(new Real(42.3), new RealVar("v1")))
                        ),
                        new Eq(
                                new IntConst("c0", new Int(10)),
                                new Times(new Int(0), new IntVar("v0"), new Times(new Real(42.3), new RealVar("v1")))
                        ),
                        new Eq(
                                new BoolConst("c1", new Or(new False(), new BoolVar("v2"))),
                                new Div(new Int(0), new IntVar("v0"), new Div(new Real(42.3), new RealVar("v1")))
                        )
                ).accept(new ExprPrimer())
        );
    }

    @Test
    public void visitRealConst_ok() {
        assertEquals(new RealConst("c0", new Real(42.3)), new RealConst("c0", new Real(42.3)).accept(new ExprPrimer()));
        assertNotEquals(new RealConst("c0", new Real(42.3)), new RealConst("c0", new Real(42.4)).accept(new ExprPrimer()));
    }

    @Test
    public void visitSetConst_ok() {
        assertEquals(new SetConst("s0", new Range(new IntVar("v0"), new IntConst("v1", new Int(42)))), new SetConst("s0", new Range(new IntVar("v0"), new IntConst("v1", new Int(42)))).accept(new ExprPrimer()));
    }

    @Test
    public void visitBoolVar_ok() {
        assertEquals(new BoolVar("v0" + PRIME_SUFFIX), new BoolVar("v0").accept(new ExprPrimer()));
    }

    @Test
    public void visitIntConst_ok() {
        assertEquals(new IntConst("c0", new Int(42)), new IntConst("c0", new Int(42)).accept(new ExprPrimer()));
        assertNotEquals(new IntConst("c0", new Int(42)), new IntConst("c0", new Int(43)).accept(new ExprPrimer()));
    }

    @Test
    public void visitStringConst_ok() {
        assertEquals(new StringConst("c0", new StringVal("ok")), new StringConst("c0", new StringVal("ok")).accept(new ExprPrimer()));
        assertNotEquals(new StringConst("c0", new StringVal("ok")), new StringConst("c0", new StringVal("ko")).accept(new ExprPrimer()));
    }

    @Test
    public void visitSetVar_ok() {
        assertEquals(new SetVar("v0" + PRIME_SUFFIX), new SetVar("v0").accept(new ExprPrimer()));
    }

    @Test
    public void visitIntVar_ok() {
        assertEquals(new IntVar("v0" + PRIME_SUFFIX), new IntVar("v0").accept(new ExprPrimer()));
    }

    @Test
    public void visitRealVar_ok() {
        assertEquals(new RealVar("v0" + PRIME_SUFFIX), new RealVar("v0").accept(new ExprPrimer()));
    }

    @Test
    public void visitStringVar_ok() {
        assertEquals(new StringVar("v0" + PRIME_SUFFIX), new StringVar("v0").accept(new ExprPrimer()));
    }

    @Test
    public void visitBoolConst_ok() {
        assertEquals(new BoolConst("c0", new False()), new BoolConst("c0", new False()).accept(new ExprPrimer()));
        assertNotEquals(new BoolConst("c0", new False()), new BoolConst("c0", new True()).accept(new ExprPrimer()));
    }

    @Test
    public void visitFunCall_ok() {
        assertEquals(new FunCall("c0" + PRIME_SUFFIX, new IntVar("v0")), new FunCall("c0", new IntVar("v0")).accept(new ExprPrimer()));
        assertNotEquals(new FunCall("c0" + PRIME_SUFFIX, new IntVar("v0")), new FunCall("c0", new IntVar("v1")).accept(new ExprPrimer()));
        assertNotEquals(new FunCall("c0" + PRIME_SUFFIX, new RealVar("v0")), new FunCall("c0", new RealVar("v1")).accept(new ExprPrimer()));
    }

}