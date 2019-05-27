package tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Created by gvoiron on 28/05/19.
 * Time : 00:00
 */
class TupleTest {

    @Test
    void equals_ok() {
        Tuple<String, String> tuple = new Tuple<>("test", "test");
        assertEquals(tuple, tuple);
        assertEquals(tuple, new Tuple<>("test", "test"));
        assertEquals(new Tuple<>("test", "test"), new Tuple<>("test", "test"));
    }

    @Test
    void equals_notOk() {
        assertNotEquals(new Tuple<>("test", "test"), new Tuple<>("tes", "tes"));
        assertNotEquals(new Tuple<>("test", "test"), new Tuple<>("test", "tes"));
        assertNotEquals(new Tuple<>("test", "test"), new Tuple<>("tes", "test"));
    }

    @Test
    void hashCode_ok() {
        Tuple<String, String> tuple = new Tuple<>("test", "test");
        assertEquals(tuple.hashCode(), tuple.hashCode());
    }

    @Test
    void toString_ok() {
        assertEquals("<test, test>", new Tuple<>("test", "test").toString());
    }

}