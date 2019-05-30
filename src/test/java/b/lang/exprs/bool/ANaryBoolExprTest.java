package b.lang.exprs.bool;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Created by gvoiron on 30/05/19.
 * Time : 00:56
 */
class ANaryBoolExprTest {

    @Test
    void getOperands_ok() {
        And and1 = new And();
        And and2 = new And(new True(), new False(), new False(), new True());
        And and3 = new And(new False(), new False(), new False());
        And and4 = new And(and3, new True(), new False(), and3);
        Or or1 = new Or();
        Or or2 = new Or(new True(), new False(), new False(), new True());
        Or or3 = new Or(new False(), new False(), new False());
        Or or4 = new Or(or3, new True(), new False(), or3);
        And and5 = new And(and1, and2, and3, and4, or1, or2, or3, or4);
        assertEquals(new ArrayList<>(), and1.getOperands());
        assertEquals(Arrays.asList(new True(), new False()), and2.getOperands());
        assertEquals(Collections.singletonList(new False()), and3.getOperands());
        assertEquals(Arrays.asList(and3, new True(), new False()), and4.getOperands());
        assertEquals(new ArrayList<>(), or1.getOperands());
        assertEquals(Arrays.asList(new True(), new False()), or2.getOperands());
        assertEquals(Collections.singletonList(new False()), or3.getOperands());
        assertEquals(Arrays.asList(or3, new True(), new False()), or4.getOperands());
        assertEquals(Arrays.asList(and1, and2, and3, and4, or1, or2, or4), and5.getOperands());
    }

    @Test
    void equals_ok() {
        assertEquals(new And(), new And());
        assertEquals(new Or(), new Or());
        assertNotEquals(new And(), new True());
        assertNotEquals(new Or(), new False());
    }

}