package hr.fer.zemris.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplexRootedPolynomialTest {

    @Test
    void testToString() {
        ComplexRootedPolynomial crp =
                new ComplexRootedPolynomial(new Complex(2,0), Complex.ONE, Complex.ONE_NEG, Complex.IM, Complex.IM_NEG);
        assertEquals("(2.0+i0.0)*(z-(1.0+i0.0))*(z-(-1.0+i0.0))*(z-(0.0+i1.0))*(z-(0.0-i1.0))",crp.toString());
    }
    @Test
    void test1() {
        ComplexRootedPolynomial crp =
                new ComplexRootedPolynomial(new Complex(2,0), Complex.ONE, Complex.ONE_NEG, Complex.IM, Complex.IM_NEG);
        System.out.println(crp.apply(new Complex()));
        assertEquals(new Complex(-2.0,0.0),crp.apply(new Complex()));

    }
    @Test
    void test2(){
        ComplexRootedPolynomial crp = new ComplexRootedPolynomial(new Complex(2,0), Complex.ONE, Complex.ONE_NEG, Complex.IM, Complex.IM_NEG);
        ComplexPolynomial cp = crp.toComplexPolynom();
        System.out.println(cp);
    }
    @Test
    void test3(){
        ComplexRootedPolynomial crp = new ComplexRootedPolynomial(new Complex(2,0), Complex.ONE, Complex.ONE_NEG, Complex.IM, Complex.IM_NEG);
        assertEquals(2,crp.indexOfClosestRootFor(Complex.IM,1));
    }
}