package b.lang.exprs.arith;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Created by gvoiron on 30/05/19.
 * Time : 01:51
 */
class ANaryArithExprTest {

    @Test
    void getOperands_ok() {
        Plus plus1 = new Plus();
        Plus plus2 = new Plus(new Int(42), new Real(3.42), new Real(3.42), new Int(42));
        Plus plus3 = new Plus(new Real(3.42), new Real(3.42), new Real(3.42));
        Plus plus4 = new Plus(plus3, new Int(42), new Real(3.42), plus3);
        Minus minus1 = new Minus();
        Minus minus2 = new Minus(new Int(42), new Real(3.42), new Real(3.42), new Int(42));
        Minus minus3 = new Minus(new Real(3.42), new Real(3.42), new Real(3.42));
        Minus minus4 = new Minus(minus3, new Int(42), new Real(3.42), minus3);
        Plus plus5 = new Plus(plus1, plus2, plus3, plus4, minus1, minus2, minus3, minus4);
        assertEquals(new ArrayList<>(), plus1.getOperands());
        assertEquals(Arrays.asList(new Int(42), new Real(3.42), new Real(3.42), new Int(42)), plus2.getOperands());
        assertEquals(Arrays.asList(new Real(3.42), new Real(3.42), new Real(3.42)), plus3.getOperands());
        assertEquals(Arrays.asList(plus3, new Int(42), new Real(3.42), plus3), plus4.getOperands());
        assertEquals(new ArrayList<>(), minus1.getOperands());
        assertEquals(Arrays.asList(new Int(42), new Real(3.42), new Real(3.42), new Int(42)), minus2.getOperands());
        assertEquals(Arrays.asList(new Real(3.42), new Real(3.42), new Real(3.42)), minus3.getOperands());
        assertEquals(Arrays.asList(minus3, new Int(42), new Real(3.42), minus3), minus4.getOperands());
        assertEquals(Arrays.asList(plus1, plus2, plus3, plus4, minus1, minus2, minus3, minus4), plus5.getOperands());
    }

    @Test
    void equals_ok() {
        Plus plus1 = new Plus();
        Plus plus2 = new Plus(new Int(42), new Real(3.42), new Real(3.42), new Int(42));
        Plus plus3 = new Plus(new Real(3.42), new Real(3.42), new Real(3.42));
        Plus plus4 = new Plus(plus3, new Int(42), new Real(3.42), plus3);
        Minus minus1 = new Minus();
        Minus minus2 = new Minus(new Int(42), new Real(3.42), new Real(3.42), new Int(42));
        Minus minus3 = new Minus(new Real(3.42), new Real(3.42), new Real(3.42));
        Minus minus4 = new Minus(minus3, new Int(42), new Real(3.42), minus3);
        Plus plus5 = new Plus(plus1, plus2, plus3, plus4, minus1, minus2, minus3, minus4);
        assertEquals(plus1, plus1);
        assertEquals(minus4, minus4);
        assertEquals(plus1, new Plus());
        assertEquals(new Plus(new Plus(), new Plus(new Int(42), new Real(3.42), new Real(3.42), new Int(42)), new Plus(new Real(3.42), new Real(3.42), new Real(3.42)), new Plus(plus3, new Int(42), new Real(3.42), plus3), new Minus(), new Minus(new Int(42), new Real(3.42), new Real(3.42), new Int(42)), new Minus(new Real(3.42), new Real(3.42), new Real(3.42)), new Minus(minus3, new Int(42), new Real(3.42), minus3)), plus5);
        assertNotEquals(plus1, minus1);
        assertNotEquals(plus2, minus2);
        assertNotEquals(plus3, minus3);
        assertNotEquals(plus4, minus4);
        assertNotEquals(new Plus(new Plus(), new Plus(new Int(42), new Real(3.43), new Real(3.42), new Int(42)), new Plus(new Real(3.42), new Real(3.42), new Real(3.42)), new Plus(plus3, new Int(42), new Real(3.42), plus3), new Minus(), new Minus(new Int(42), new Real(3.42), new Real(3.42), new Int(42)), new Minus(new Real(3.42), new Real(3.42), new Real(3.42)), new Minus(minus3, new Int(42), new Real(3.42), minus3)), plus5);
    }

}