package hr.fer.zemris.java.fractals;


import hr.fer.zemris.java.fractals.viewer.FractalViewer;
import hr.fer.zemris.java.fractals.viewer.IFractalProducer;
import hr.fer.zemris.java.fractals.viewer.IFractalResultObserver;
import hr.fer.zemris.math.Complex;
import hr.fer.zemris.math.ComplexPolynomial;
import hr.fer.zemris.math.ComplexRootedPolynomial;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicBoolean;

public class NewtonP2 {
    public static void main(String[] args) {
        int brojPoslova=16;

        if (args.length== 2) {

                if(args[0].equals("-m")){

                    brojPoslova=Integer.parseInt(args[1]);
                    System.out.println(brojPoslova);
                }
        }else if (args.length==1){
            String[] a=args[0].split("=");
            int b=Integer.parseInt(a[1]);
            if(a[0].equals("--mintracks")){

                brojPoslova=b;
            }
        }


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
        System.out.println("Image of fractal will appear shortly. Thank you.");
        Complex[]polje=new Complex[list.size()];
        list.toArray(polje);
        if(list.size()>=2) {
           NewtonP2.MojParalelniProducer producer=new NewtonP2.MojParalelniProducer(new ComplexRootedPolynomial(Complex.ONE, polje),brojPoslova);
            producer.setup();
            FractalViewer.show(producer);
            producer.close();
        }else {
            System.out.println("Molimo unesite više korijena");
        }
    }
    public static class RadnaDretva extends RecursiveAction {
        private static final long serialVersionUID = 1L;
        int inside;
        int samples;
        ComplexRootedPolynomial root;
        double reMin;
        double reMax;
        double imMin;
        double imMax;
        int width;
        int height;
        int yMin;
        int yMax;
        int m;
        short[] data;
        AtomicBoolean cancel;
      //  public static RadnaDretva NO_JOB = new RadnaDretva();


        @Override
        protected void compute() {
            if(samples <= 10_000) {
                computeDirect();
                return;
            }
            System.out.println(1);
            int bPoslova1=samples/2;
            int brojTraka1 =bPoslova1;
            int brojYPoTraci1 = height / brojTraka1;
            int yMin1 = brojTraka1*brojYPoTraci1;
            int yMax1 = (brojTraka1+1)*brojYPoTraci1-1;
            if(bPoslova1==brojTraka1-1) {
                yMax1 = height-1;
            }
            RadnaDretva p1 = new RadnaDretva(bPoslova1,root,reMin, reMax, imMin, imMax, width, height, yMin1, yMax1, m, data, cancel);
            int bPoslova2=samples-samples/2;
            int brojTraka2 =bPoslova2;
            int brojYPoTraci2 = height / brojTraka2;
            int yMin2 = brojTraka2*brojYPoTraci2;
            int yMax2 = (brojTraka2+1)*brojYPoTraci2-1;
            if(bPoslova2==brojTraka2-1) {
                yMax2 = height-1;
            }
            RadnaDretva p2 = new RadnaDretva(bPoslova2,root,reMin, reMax, imMin, imMax, width, height, yMin2, yMax2, m, data, cancel);
            invokeAll(p1, p2);
            inside = p1.inside + p2.inside;

        }

        public RadnaDretva(int samples, ComplexRootedPolynomial root,double reMin, double reMax, double imMin,
                             double imMax, int width, int height, int yMin, int yMax,
                             int m, short[] data, AtomicBoolean cancel) {
            super();
            this.samples=samples;
            this.root=root;
            this.reMin = reMin;
            this.reMax = reMax;
            this.imMin = imMin;
            this.imMax = imMax;
            this.width = width;
            this.height = height;
            this.yMin = yMin;
            this.yMax = yMax;
            this.m = m;
            this.data = data;
            this.cancel = cancel;
        }
        private void computeDirect() {
            MojMendelbrot.calculate(root,reMin, reMax, imMin, imMax, width, height, m, yMin, yMax, data, cancel);
        }

        public void run() {

            MojMendelbrot.calculate(root,reMin, reMax, imMin, imMax, width, height, m, yMin, yMax, data, cancel);

        }
    }


    public static class MojParalelniProducer implements IFractalProducer {
        private ComplexRootedPolynomial rootedPolynomial;
        private int bPoslova;
        ForkJoinPool pool;

        public MojParalelniProducer(ComplexRootedPolynomial rootedPolynomial, int bPoslova) {
            this.rootedPolynomial = rootedPolynomial;
            this.bPoslova = bPoslova;
        }

        @Override
        public void produce(double reMin, double reMax, double imMin, double imMax,
                            int width, int height, long requestNo, IFractalResultObserver observer, AtomicBoolean cancel) {
            System.out.println("Zapocinjem izracun...");
            System.out.println("Broj mini traka:"+ bPoslova);
            int m = 16*16*16;
            short[] data = new short[width * height];


            int yMin = 0;
            int yMax = height-1;

            final BlockingQueue<RadnaDretva> queue = new LinkedBlockingQueue<>();
            RadnaDretva p = new RadnaDretva(bPoslova,rootedPolynomial,reMin, reMax, imMin, imMax, width, height, yMin, yMax, m, data, cancel);
            pool.invoke(p);

            System.out.println("Racunanje gotovo. Idem obavijestiti promatraca tj. GUI!");
            ComplexPolynomial polinom=rootedPolynomial.toComplexPolynom();
            observer.acceptResult(data, (short)(polinom.order()+1), requestNo);
        }

        @Override
        public void setup() {
            this.pool = new ForkJoinPool();

        }

        @Override
        public void close() {
            pool.shutdown();
        }
    }
}
