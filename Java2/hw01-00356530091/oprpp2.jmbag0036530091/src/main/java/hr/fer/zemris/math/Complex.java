package hr.fer.zemris.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Complex {
    private double real,imag;

    public static final Complex ZERO = new Complex(0,0);
    public static final Complex ONE = new Complex(1,0);
    public static final Complex ONE_NEG = new Complex(-1,0);
    public static final Complex IM = new Complex(0,1);
    public static final Complex IM_NEG = new Complex(0,-1);

    public double getReal() {
        return real;
    }

    public double getImag() {
        return imag;
    }

    public Complex() {
        this.real=0;
        this.imag=0;
    }

    public Complex(double re, double im) {
        this.real=re;
        this.imag=im;
    }


    // returns module of complex number
    public double module() {
        return  Math.sqrt(real*real+imag*imag);
    }


    // returns this*c
     public Complex multiply(Complex c) {
        double a = this.real;
        double b= this.imag;
        double c2 = c.real;
        double d= c.imag;

        return new Complex((a*c2 - b*d),(a*d + b*c2));

     }

    // returns this/c
     public Complex divide(Complex c) {
        double a = this.real;
         double b= this.imag;
         double c2 = c.real;
         double d= c.imag;

         return new Complex((a*c2 + b*d)/(c2*c2+d*d),(  b*c2-a*d)/(c2*c2+d*d));
    }

    // returns this+c
     public Complex add(Complex c) {
         double a = this.real;
         double b= this.imag;
         double c2 = c.real;
         double d= c.imag;
         return new Complex((a+c2),(b+d));
     }

    // returns this-c
     public Complex sub(Complex c) {
         double a = this.real;
         double b= this.imag;
         double c2 = c.real;
         double d= c.imag;
         return new Complex((a-c2),(b-d));
     }

    // returns -this
     public Complex negate() {
         return new Complex((-1.0*this.real),(-1.0*this.imag));
     }

    // returns this^n, n is non-negative integer
     public Complex power(int n) {
        if(n==0){
            return new Complex(1,0);
        }
        Complex rez = new Complex(real,imag);
        for(int i =0;i<n-1;i++){
            rez=rez.multiply(this);
        }
        return rez;
     }

    // returns n-th root of this, n is positive integer
     public List<Complex> root(int n) {
        List<Complex> rez=new ArrayList<>();
        if(n<0) throw new IllegalArgumentException();
         double a = this.real;
         double b= this.imag;
         double m= this.module();
         double alpha= Math.atan(b/a);

         m=Math.pow(m,1/(n*1.0));

         for( int k=0;k<n;k++){

             double beta = (alpha + k*Math.PI*2)/n;

             double x = 1.0;
             double y=1.0;

             rez.add(new Complex(x*m*Math.cos(beta),y*m*Math.sin(beta)));
         }
       return rez;
     }

     @Override
     public String toString() {
        if(getImag()>=0) {
            return this.real + "+i" + Math.abs(this.imag);
        }else {
            return this.real + "-i" + (Math.abs(this.imag));
        }
     }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complex complex = (Complex) o;
        return Double.compare(complex.real, real) == 0 && Double.compare(complex.imag, imag) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imag);
    }
}
