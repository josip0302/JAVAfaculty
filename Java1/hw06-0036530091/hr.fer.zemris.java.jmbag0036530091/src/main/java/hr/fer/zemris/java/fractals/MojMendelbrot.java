package hr.fer.zemris.java.fractals;

import hr.fer.zemris.java.fractals.viewer.IFractalProducer;
import hr.fer.zemris.java.fractals.viewer.IFractalResultObserver;
import hr.fer.zemris.math.Complex;
import hr.fer.zemris.math.ComplexPolynomial;
import hr.fer.zemris.math.ComplexRootedPolynomial;

import java.util.concurrent.atomic.AtomicBoolean;

public class MojMendelbrot {

    public static void calculate(ComplexRootedPolynomial rootedPolynomial,
                                 double reMin, double reMax, double imMin, double imMax,
                                 int width, int height, int m, int ymin, int ymax, short[] data, AtomicBoolean cancel) {
        ComplexPolynomial polinom=rootedPolynomial.toComplexPolynom();
        ComplexPolynomial derived=polinom.derive();
        int offset = ymin * width;
        for(int y = ymin; y <= ymax; y++) {
            if(cancel.get()) break;
            for(int x = 0; x < width; x++) {
                double cre = x / (width-1.0) * (reMax - reMin) + reMin;
                double cim = (height-1.0-y) / (height-1) * (imMax - imMin) + imMin;
                double zre = 0;
                double zim = 0;
                Complex zn = new Complex(cre, cim);
                Complex znold;
                double module = 0;
                int iters = 0;
                do {
                    Complex numerator = polinom.apply(zn);
                    Complex denominator = derived.apply(zn);
                    znold = zn;
                    Complex fraction = numerator.divide(denominator);
                    zn = zn.sub(fraction);
                    module = znold.sub(zn).module();
                    iters++;
                } while(Math.abs(module)>0.001&&iters < m );
                int index = (rootedPolynomial.indexOfClosestRootFor(zn, 0.002));

                data[offset] =(short) (index+1);
                offset++;

            }
        }

    }

}

