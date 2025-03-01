package hr.fer.zemris.java.gui.layouts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RCPositionTest {
    @Test
    void Test1 (){
        RCPosition position=RCPosition.parse("3,7");
        assertEquals(3,position.getRow());
        assertEquals(7,position.getColumn());
    }

}