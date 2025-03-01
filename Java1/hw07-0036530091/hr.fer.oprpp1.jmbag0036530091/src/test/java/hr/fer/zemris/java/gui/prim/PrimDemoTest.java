package hr.fer.zemris.java.gui.prim;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimDemoTest {
    @Test
    void test1(){
        PrimDemo.PrimListModel model = new PrimDemo.PrimListModel();
        model.next();
        assertEquals(2,model.getSize());
    }
    @Test
    void test2(){
        PrimDemo.PrimListModel model = new PrimDemo.PrimListModel();
        model.next();
        assertEquals(2,model.getElementAt(1));
    }
    @Test
    void test3(){
        PrimDemo.PrimListModel model = new PrimDemo.PrimListModel();
        model.next();
        model.next();
        assertEquals(3,model.getElementAt(2));
    }
    @Test
    void test4(){
        PrimDemo.PrimListModel model = new PrimDemo.PrimListModel();
        model.next();
        model.next();
        model.next();
        assertEquals(5,model.getElementAt(3));
    }

}