package hr.fer.zemris.java.gui.layouts;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CalcLayoutTest {

    @Test
    void addLayoutComponentException1() {
        assertThrows(CalcLayoutException.class,()->new
                CalcLayout().addLayoutComponent(new JLabel("text"),new RCPosition(0,-2)));
    }
    @Test
    void addLayoutComponentException2() {
        assertThrows(CalcLayoutException.class,()->new
                CalcLayout().addLayoutComponent(new JLabel("text"),new RCPosition(1,4)));
    }
    @Test
    void addLayoutComponentException3() {
        CalcLayout c=new CalcLayout();
        c.addLayoutComponent(new JLabel("text1"),new RCPosition(1,1));
        assertThrows(CalcLayoutException.class,()->c.addLayoutComponent(new JLabel("text2"),new RCPosition(1,1)));
    }
    @Test
    void TestWidth(){
        JPanel p = new JPanel(new CalcLayout(2));
        JLabel l1 = new JLabel("");
        l1.setPreferredSize(new Dimension(10,30));
        JLabel l2 = new JLabel("");
        l2.setPreferredSize(new Dimension(20,15));
        p.add(l1, new RCPosition(2,2));
        p.add(l2, new RCPosition(3,3));
        Dimension dim = p.getPreferredSize();
        assertEquals(152,dim.width);
        assertEquals(158,dim.height);
    }
    @Test
    void TestWidth2(){
        JPanel p = new JPanel(new CalcLayout(2));
        JLabel l1 = new JLabel("");
        l1.setPreferredSize(new Dimension(108,15));
        JLabel l2 = new JLabel("");
        l2.setPreferredSize(new Dimension(16,30));
        p.add(l1, new RCPosition(1,1));
        p.add(l2, new RCPosition(3,3));
        Dimension dim = p.getPreferredSize();
        assertEquals(152,dim.width);
        assertEquals(158,dim.height);
    }
}