package hr.fer.zemris.math;

import java.util.ArrayList;
import java.util.List;

public class ComplexPolynomial {
    private List<Complex> factors;
    // constructor
    public ComplexPolynomial(Complex ...factors) {
        this.factors=new ArrayList<>(List.of(factors));
    }
    // returns order of this polynom; eg. For (7+2i)z^3+2z^2+5z+1 returns 3
     public short order() {
        return (short) (factors.size()-1);
     }
    // computes a new polynomial this*p
    public ComplexPolynomial multiply(ComplexPolynomial p) {
        int x=this.order()+p.order()+1;
        Complex[] list=new Complex[x];
        for(int i =0;i<factors.size();i++){
            for(int j = 0;j<=p.order();j++){
                Complex c=this.factors.get(i).multiply(p.factors.get(j));

                if(list[j+i]==null){
                    list[j+i]=c;

                }else {
                    list[j+i]=list[j+i].add(c);
                }
            }

        }
        return new ComplexPolynomial(list);
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for(int i =0;i<factors.size();i++){

            if(i!= factors.size()-1){
                sb.append("("+factors.get(i)+")*z^"+(factors.size()-i-1)+"+");
            }else {
                sb.append("("+factors.get(i)+")");
            }
        }
        return sb.toString();
    }
    public Complex apply(Complex z) {
        Complex rez=new Complex();
        int x=this.order();
        for(int i =0;i<factors.size();i++){

            rez=rez.add((factors.get(i)).multiply(z.power(x-i)));

        }
        return rez;
    }
    // computes first derivative of this polynomial; for example, for
    //  (7+2i)z^3+2z^2+5z+1 returns (21+6i)z^2+4z+5
    public ComplexPolynomial derive() {

        Complex[] polje=new Complex[this.order()];
        for(int i=0;i<this.order();i++){
            polje[i]=factors.get(i).multiply(new Complex(this.order()-i,0));
        }


        return new ComplexPolynomial(polje);
    }
}
