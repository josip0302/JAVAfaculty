package hr.fer.zemris.math;

import java.util.ArrayList;
import java.util.List;

public class ComplexRootedPolynomial {
    private Complex constant;
    private List<Complex> roots;
    public ComplexRootedPolynomial(Complex constant, Complex ... roots) {
        this.roots=new ArrayList<>();
        this.roots.addAll(List.of(roots));
        this.constant=constant;
    }

    // computes polynomial value at given point z
    public Complex apply(Complex z) {
        Complex rez=new Complex(constant.getReal(),constant.getImag());
        for(int i =0;i<roots.size();i++){

            rez=rez.multiply(z.sub(roots.get(i)));

        }
        return rez;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("("+constant.toString()+")");
        for(int i =0;i<roots.size();i++){
           sb.append("*(z-("+roots.get(i).toString()+"))");
        }
        return sb.toString();
    }
    // converts this representation to ComplexPolynomial type
    public ComplexPolynomial toComplexPolynom() {
       ComplexPolynomial rez=new ComplexPolynomial(constant);
       for(int i=0;i<roots.size();i++){

           rez=rez.multiply(new ComplexPolynomial(Complex.ONE,roots.get(i).negate()));
       }
       return rez;
    }

    // finds index of closest root for given complex number z that is within
    // treshold; if there is no such root, returns -1
    // first root has index 0, second index 1, etc
    public int indexOfClosestRootFor(Complex z, double treshold) {
        double min=z.sub(roots.get(0)).module();;
        int ind=0;
        for(int i=1;i<roots.size();i++){
            Complex c=z.sub(roots.get(i));

            if(min>c.module()){

                ind=i;
                min=c.module();

            }
        }
        if(min>treshold){
            return -1;
        }
        return ind;
    }
}
