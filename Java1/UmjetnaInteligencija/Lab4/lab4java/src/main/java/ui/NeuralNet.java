package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NeuralNet {
    String nn,filename;
    ArrayList<Neuron> list;
    ArrayList<String> inOut;
    Double error;
    public NeuralNet(String nn, String fileName) {
        filename=fileName;
        list=new ArrayList<>();
        List<String> in=Input.readCSVString(fileName);
        int d=in.get(0).split(",").length-1;
        in.remove(0);
        inOut=new ArrayList<>(in);

       this.nn=nn;
       error=0.0;
       if(nn.equals("5s")){
           list.add(new Neuron(5,d));
           list.add(new Neuron(d,5));
       } else if (nn.equals("20s")) {
           list.add(new Neuron(20,d));
           list.add(new Neuron(d,20));
       } else if (nn.equals("5s5s")) {
           list.add(new Neuron(5,d));
           list.add(new Neuron(5,5));
           list.add(new Neuron(d,5));
       }


    }


    public void setData(String filename){
        this.filename=filename;
        List<String> in=Input.readCSVString(filename);
        int d=in.get(0).split(",").length-1;
        in.remove(0);
        inOut=new ArrayList<>(in);
    }

    public ArrayList<Neuron> getList() {
        return list;
    }

    public void setList(ArrayList<Neuron> list) {
        this.list = list;
    }

    public void printErr() {
    System.out.printf("%,.6f",error);
}
public void start(){
        error=0.0;
        for (String s:inOut){
            List<Double> list1=new ArrayList<>();
            String[]a=s.split(",");
            for(int i=0;i<a.length-1;i++){
                list1.add(Double.valueOf(a[i]));
            }
            double out = Double.parseDouble(a[a.length-1]);
            error+=Math.pow(run(new Matrix((ArrayList<Double>) list1,list1.size()),out),2);
        }
        error=error/ inOut.size();
    }

    public double run(Matrix m,Double output){
        Matrix rez=m;

        for (int i = 0; i < list.size()-1; i++) {
            rez=list.get(i).impuls(rez);
            rez.sigmoid();

        }
        return list.get(list.size()-1).impuls(rez).getFirst()-output;

    }
    public static NeuralNet newNet(NeuralNet a,NeuralNet b){
        List<Neuron> na=a.getList();
        List<Neuron> nb=b.getList();
        List<Neuron> rez =new ArrayList<>();

        for (int i = 0; i < na.size() ; i++) {

            rez.add(na.get(i).duble(nb.get(i)));
        }
        NeuralNet net=new NeuralNet(a.nn,a.filename);
        net.setList((ArrayList<Neuron>) rez);
        return net;
    }

    public void mutacija(double p, double k) {
        for (int i = 0; i < list.size(); i++) {
            ArrayList<Double> stara=list.get(i).weightList;
            ArrayList<Double> nova=new ArrayList<>();
            for (int j = 0; j < stara.size(); j++) {
                double d=new Random().nextDouble();
                if(d<p){
                    nova.add(new Random().nextGaussian() * k);
                }else {
                    nova.add(stara.get(j));
                }
            }
            list.get(i).setWeightList(nova);
        }
    }
}
