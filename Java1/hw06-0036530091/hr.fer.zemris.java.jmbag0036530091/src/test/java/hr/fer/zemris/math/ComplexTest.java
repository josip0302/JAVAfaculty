package hr.fer.zemris.math;

import org.junit.jupiter.api.Test;

import javax.swing.plaf.PanelUI;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComplexTest {
    @Test
    public void Test1(){
        Complex a=new Complex(1,2);
        Complex b= new Complex(2,1);

        Complex c= a.multiply(b);
        assertEquals(0,c.getReal());
        assertEquals(5,c.getImag());
    }
    @Test
    public void Test2(){
        Complex a=new Complex(-156,78);
        Complex b= new Complex(58,-123);

        Complex c= a.multiply(b);
        assertEquals(546,c.getReal());
        assertEquals(23712,c.getImag());

    }
    @Test
    public void Test3(){
        Complex a=new Complex(-156,78);
        assertEquals("-156.0+i78.0",a.toString());

    }
    @Test
    public void Test4(){
        Complex a=new Complex(-156,-78);
        assertEquals("-156.0-i78.0",a.toString());

    }
    @Test
    public void Test5(){
        Complex a=new Complex(1,2);
        Complex b= new Complex(2,1);

        Complex c= a.divide(b);
        assertEquals(0.8,c.getReal());
        assertEquals(0.6,c.getImag());
    }
    @Test
    public void Test6(){
        Complex a=new Complex(-32,23);
        Complex b= new Complex(32,-123);

        Complex c= a.divide(b);
        assertEquals(-0.2385,Math.round(c.getReal() * 10000.0)/10000.0);
        assertEquals(-0.198,Math.round(c.getImag()* 1000.0)/1000.0);
    }
    @Test
    public void Test7(){
        Complex a=new Complex(101,-21);
        Complex b= new Complex(27,-123);

        Complex c= a.add(b);
        assertEquals(128,c.getReal() );
        assertEquals(-144,c.getImag());
    }
    @Test
    public void Test8(){
        Complex a=new Complex(101,-21);
        Complex b= new Complex(27,-123);

        Complex c= a.sub(b);
        assertEquals(74,c.getReal() );
        assertEquals(102,c.getImag());
    }
    @Test
    public void Test9(){
        Complex a=new Complex(1,2);

        assertEquals(Math.sqrt(5),a.module());
    }
    @Test
    public void Test10(){
        Complex a=new Complex(1,2);

        assertEquals(new Complex(-1,-2),a.negate());
    }

    @Test
    public void Test11(){
        Complex a=new Complex(1,2);

        assertEquals(new Complex(41,-38),a.power(5));
    }
    @Test
    public void Test12(){
        Complex a=new Complex(1,2);

        assertEquals(new Complex(1,2),a.power(1));
    }
    @Test
    public void Test13(){
        Complex a=new Complex(0,4);
        List<Complex> lista= a.root(2);
        for (int i =0;i<2;i++){
            System.out.println(lista.get(i));
        }
    }
    @Test
    public void Test14(){
        Complex a=new Complex(45,55);
        List<Complex> lista= a.root(5);
        for (int i =0;i<5;i++){
            System.out.println(lista.get(i));
        }
    }


}