package hr.fer.oprpp1.hw05.crypto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    public void hextobyteTest1(){
        assertArrayEquals( new byte[] {1, 2, 3},Util.hextobyte("010203"));
    }
    @Test
    public void hextobyteTest2(){
        assertArrayEquals( new byte[] {1, -82, 34},Util.hextobyte("01aE22"));
    }
    @Test
    public void hextobyteTest3(){
        assertArrayEquals( new byte[] {10, 11, 12},Util.hextobyte("0a0b0c"));
    }
    @Test
    public void hextobyteIllegalArgument1(){
        assertThrows( IllegalArgumentException.class,()->Util.hextobyte("01GE22"));
    }
    @Test
    public void hextobyteIllegalArgument2(){
        assertThrows( IllegalArgumentException.class,()->Util.hextobyte("01E22"));
    }
    @Test
    public void byteToHexTest1(){
        assertEquals( "010203",Util.bytetohex(new byte[] {1, 2, 3}));
    }

    @Test
    public void byteToHexTest2(){
        assertEquals( "01ae22",Util.bytetohex(new byte[] {1, -82, 34}));
    }
    @Test
    public void byteToHexTest3(){
        assertEquals( "0a0b0c",Util.bytetohex(new byte[] {10, 11, 12}));
    }

}