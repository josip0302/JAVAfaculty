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
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class NewtonParallel {
    public static void main(String[] args) {
        int brojRadnika=0;
        int brojPoslova=0;
        int dostupni=Runtime.getRuntime().availableProcessors();
        if (args.length== 2) {
            if(args[0].length()>2){
                String a=args[0].split("=")[1];
                String b=args[1].split("=")[1];

                brojRadnika=Integer.parseInt(a);
                brojPoslova=Integer.parseInt(b);

            }else {
                if(args[0].equals("-w")){
                    brojRadnika=Integer.parseInt(args[1]);
                    System.out.println(brojRadnika);
                    brojPoslova=dostupni*4;
                }else {
                    brojPoslova=Integer.parseInt(args[1]);
                    System.out.println(brojPoslova);
                    brojRadnika=dostupni;

                }
            }


        } else if (args.length== 4) {
            String a ;
            String b ;
            if(args[0].equals("-w")) {
               a = args[1];
               b = args[3];
            }else {
                 a = args[3];
                 b = args[1];
            }
            brojRadnika=Integer.parseInt(a);
            brojPoslova=Integer.parseInt(b);

        }else if (args.length==1){
            String[] a=args[0].split("=");
            int b=Integer.parseInt(a[1]);
            if(a[0].equals("--workers")){
                brojRadnika=b;
                brojPoslova=dostupni*4;
            }else {
                brojRadnika=dostupni;
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
            FractalViewer.show(new MojParalelniProducer(new ComplexRootedPolynomial(Complex.ONE, polje),brojRadnika,brojPoslova));
        }else {
            System.out.println("Molimo unesite više korijena");
        }
    }
    public static class RadnaDretva implements Runnable {
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
        public static RadnaDretva NO_JOB = new RadnaDretva();

        private RadnaDretva() {
        }

        public RadnaDretva( ComplexRootedPolynomial root,double reMin, double reMax, double imMin,
                             double imMax, int width, int height, int yMin, int yMax,
                             int m, short[] data, AtomicBoolean cancel) {
            super();
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

        @Override
        public void run() {

            MojMendelbrot.calculate(root,reMin, reMax, imMin, imMax, width, height, m, yMin, yMax, data, cancel);

        }
    }


    public static class MojParalelniProducer implements IFractalProducer {
        private ComplexRootedPolynomial rootedPolynomial;
        private int bRadnika,bPoslova;

        public MojParalelniProducer(ComplexRootedPolynomial rootedPolynomial, int bRadnika, int bPoslova) {
            this.rootedPolynomial = rootedPolynomial;
            this.bRadnika = bRadnika;
            this.bPoslova = bPoslova;
        }

        @Override
        public void produce(double reMin, double reMax, double imMin, double imMax,
                            int width, int height, long requestNo, IFractalResultObserver observer, AtomicBoolean cancel) {
            System.out.println("Zapocinjem izracun...");
            int m = 16*16*16;
            short[] data = new short[width * height];
            final int brojTraka =bPoslova;
            int brojYPoTraci = height / brojTraka;

            final BlockingQueue<RadnaDretva> queue = new LinkedBlockingQueue<>();

            Thread[] radnici = new Thread[bRadnika];
            for(int i = 0; i < radnici.length; i++) {
                radnici[i] = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true) {
                            RadnaDretva p = null;
                            try {
                                p = queue.take();
                                if(p==RadnaDretva.NO_JOB) break;
                            } catch (InterruptedException e) {
                                continue;
                            }
                            p.run();
                        }
                    }
                });
            }
            for(int i = 0; i < radnici.length; i++) {
                radnici[i].start();
            }

            for(int i = 0; i < brojTraka; i++) {
                int yMin = i*brojYPoTraci;
                int yMax = (i+1)*brojYPoTraci-1;
                if(i==brojTraka-1) {
                    yMax = height-1;
                }
                RadnaDretva posao = new RadnaDretva(rootedPolynomial,reMin, reMax, imMin, imMax, width, height, yMin, yMax, m, data, cancel);
                while(true) {
                    try {
                        queue.put(posao);
                        break;
                    } catch (InterruptedException e) {
                    }
                }
            }
            for(int i = 0; i < radnici.length; i++) {
                while(true) {
                    try {
                        queue.put(RadnaDretva.NO_JOB);
                        break;
                    } catch (InterruptedException e) {
                    }
                }
            }

            for(int i = 0; i < radnici.length; i++) {
                while(true) {
                    try {
                        radnici[i].join();
                        break;
                    } catch (InterruptedException e) {
                    }
                }
            }

            System.out.println("Racunanje gotovo. Idem obavijestiti promatraca tj. GUI!");
            ComplexPolynomial polinom=rootedPolynomial.toComplexPolynom();
            observer.acceptResult(data, (short)(polinom.order()+1), requestNo);
        }
    }
}
