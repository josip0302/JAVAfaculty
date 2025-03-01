package hr.fer.zemris.java.fractals;

import hr.fer.zemris.math.Complex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;


import hr.fer.zemris.java.fractals.viewer.FractalViewer;
import hr.fer.zemris.java.fractals.viewer.IFractalProducer;
import hr.fer.zemris.java.fractals.viewer.IFractalResultObserver;
import hr.fer.zemris.math.ComplexPolynomial;
import hr.fer.zemris.math.ComplexRootedPolynomial;

public class Newton {
    public static void main(String[] args) {
        System.out.println("Welcome to Newton-Raphson iteration-based fractal viewer");
        System.out.println("Please enter at least two roots, one root per line. Enter 'done' when done.");
        Scanner sc=new Scanner(System.in);
        List<Complex> list=new ArrayList<>();
        int counter=1;
        String in;
        do {
            System.out.print("root "+counter+">");
             in = sc.nextLine();
             in=in.strip();
            if(in.equals("done")){
                continue;
            }

           String[] polje=  in.split("\\s+");
           Complex c;
           if(polje.length==1){
               if(polje[0].length()==1) {
                   if (polje[0].contains("i")) {
                       list.add(new Complex(0, 1));
                   } else {
                       list.add(new Complex(Double.parseDouble(polje[0]), 0));
                   }
               }else{
                   if (polje[0].contains("i")) {
                       list.add(new Complex(0,Double.parseDouble(polje[0].replace("i", ""))));
                   } else {
                       list.add(new Complex(Double.parseDouble(polje[0]), 0));
                   }

               }
           }else if(polje.length==3){
                double a= Double.parseDouble(polje[0]);
                double b= Double.parseDouble(polje[2].replace("i", ""));
                if(polje[1].equals("-")){
                    b*=(-1.0);
                }
               list.add(new Complex(a, b));
           }else {
               counter--;
           }
            counter++;


        }while (!in.equals("done"));


        Complex[]polje=new Complex[list.size()];
        list.toArray(polje);
    if(list.size()>=2) {
            System.out.println("Image of fractal will appear shortly. Thank you.");
            FractalViewer.show(new Producer(new ComplexRootedPolynomial(Complex.ONE, polje)));
        }else {
            System.out.println("Molimo unesite više korijena");
        }
    }

    public static class Producer implements IFractalProducer {
        private ComplexRootedPolynomial rootedPolynomial;

        public Producer(ComplexRootedPolynomial rootedPolynomial) {
            this.rootedPolynomial = rootedPolynomial;
        }

        @Override
        public void produce(double reMin, double reMax, double imMin, double imMax,
                            int width, int height, long requestNo, IFractalResultObserver observer, AtomicBoolean cancel) {
            System.out.println("Zapocinjem izracun...");
            int m = 16*16*16;
            int offset = 0;
            short[] data = new short[width * height];
            MojMendelbrot.calculate(rootedPolynomial,reMin, reMax, imMin, imMax, width, height, m, 0, height - 1, data, cancel);
        /*
        for(int y = 0; y < height; y++) {
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

                data[offset++] =(short) (index+1);

            }
        }*/
            System.out.println("Racunanje gotovo. Idem obavijestiti promatraca tj. GUI!");
            ComplexPolynomial polinom=rootedPolynomial.toComplexPolynom();
            observer.acceptResult(data, (short)(polinom.order()+1), requestNo);
        }}

}
