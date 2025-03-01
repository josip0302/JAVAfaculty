package hr.fer.zemris.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplexPolynomialTest {

    @Test
    void test1() {
        ComplexPolynomial cp =new ComplexPolynomial(new Complex(2,0), new Complex(), new Complex(), new Complex(), new Complex(-2.0,0.0));
        assertEquals("(2.0+i0.0)*z^4+(0.0+i0.0)*z^3+(0.0+i0.0)*z^2+(0.0+i0.0)*z^1+(-2.0+i0.0)",cp.toString());
    }
    @Test
    void test2() {
        ComplexPolynomial cp =new ComplexPolynomial(new Complex(2,0), new Complex(), new Complex(), new Complex(), new Complex(-2.0,0.0));

        assertEquals(4,cp.order());
    }

    @Test
    void test3(){
        ComplexPolynomial cp =new ComplexPolynomial(new Complex(1,0), new Complex(1,0), new Complex(1,0), new Complex(1,0), new Complex(1,0));

        assertEquals(8,cp.multiply(cp).order());
    }
    @Test
    void test4(){
        ComplexPolynomial cp =new ComplexPolynomial(new Complex(1,0), new Complex(1,0), new Complex(1,0), new Complex(1,0), new Complex(1,0));
        ComplexPolynomial cp2 =new ComplexPolynomial( new Complex(1,0), new Complex(1,0), new Complex(-1,0));
        System.out.println(cp.multiply(cp2));

        assertEquals(6,cp.multiply(cp2).order());
    }
    @Test
    void test5(){
        ComplexPolynomial cp =new ComplexPolynomial(new Complex(1,0), new Complex(1,0), new Complex(1,0), new Complex(1,0), new Complex(1,0));

        assertEquals(new Complex(31,0),cp.apply(new Complex(2,0)));
    }

    @Test
    void Test6() {
        ComplexPolynomial cp =new ComplexPolynomial(new Complex(7,2), new Complex(2,0), new Complex(5,0), new Complex(1,0));
        assertEquals("(21.0+i6.0)*z^2+(4.0+i0.0)*z^1+(5.0+i0.0)",cp.derive().toString());
    }
}