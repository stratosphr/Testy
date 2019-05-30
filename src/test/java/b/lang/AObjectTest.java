package b.lang;

import b.lang.exprs.arith.IntVar;
import b.lang.exprs.arith.RealVar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Created by gvoiron on 29/05/19.
 * Time : 15:42
 */
class AObjectTest {

    @Test
    void hashCode_ok() {
        RealVar realFB = new RealVar("FB");
        RealVar realEa = new RealVar("Ea");
        IntVar intFB = new IntVar("FB");
        IntVar intEa = new IntVar("Ea");
        assertEquals(realFB.hashCode(), realFB.hashCode());
        assertEquals(intFB.hashCode(), intFB.hashCode());
        assertNotEquals(intFB.hashCode(), realFB.hashCode());
        assertNotEquals(intFB.hashCode(), realEa.hashCode());
        assertNotEquals(intEa.hashCode(), realFB.hashCode());
        assertNotEquals(intEa.hashCode(), realEa.hashCode());
    }

    @Test
    void equals_ok() {
        RealVar realFB = new RealVar("FB");
        RealVar realEa = new RealVar("Ea");
        IntVar intFB = new IntVar("FB");
        IntVar intEa = new IntVar("Ea");
        IntVar nullVar = new IntVar("null");
        assertEquals(realFB, realFB);
        assertEquals(intFB, intFB);
        assertEquals(realFB, new RealVar("FB"));
        assertEquals(intFB, new IntVar("FB"));
        assertEquals(realEa, new RealVar("Ea"));
        assertEquals(intEa, new IntVar("Ea"));
        assertNotEquals(realFB, realEa);
        assertNotEquals(realFB, intFB);
        assertNotEquals(realFB, intEa);
        assertNotEquals(intFB, realFB);
        assertNotEquals(intFB, realEa);
        assertNotEquals(intFB, intEa);
        assertNotEquals(null, nullVar);
        assertNotEquals(new RealVar("Ea"), new RealVar("FB"));
        assertNotEquals(new IntVar("Ea"), new IntVar("FB"));
        assertNotEquals(new RealVar("Ea"), new RealVar("Test"));
        assertNotEquals(new IntVar("Ea"), new IntVar("Test"));
    }

}