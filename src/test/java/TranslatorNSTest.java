import org.junit.Test;

import static org.junit.Assert.*;

public class TranslatorNSTest {
    static class CharInt {
        public static char newChar = 'A';
        public static int newInt = 10;
    }

    @Test
    public void pow() throws Exception {
        assertEquals(25, TranslatorNS.pow(5, 2), 0);
    }

    @Test
    public void transformTo10() throws Exception {
        assertEquals(CharInt.newInt, TranslatorNS.transformTo10('a'));
    }

    @Test
    public void transformTo16() throws Exception {
        assertEquals(CharInt.newChar, TranslatorNS.transformTo16(10));
    }

    @Test
    public void translator() throws Exception {
        assertEquals("" + CharInt.newChar, TranslatorNS.translator("10", 10, 16));
    }

}