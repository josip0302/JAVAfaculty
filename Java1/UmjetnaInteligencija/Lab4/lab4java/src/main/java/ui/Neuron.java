package ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Neuron {
    int d;
    int z;
    ArrayList<Double> weightList;
    Matrix weight;
    public Neuron(int d) {
        this.d = d;
        ArrayList<Double> weightList=new ArrayList<>();
        for(int i=0;i<d;i++){
            weightList.add(new Random().nextGaussian() * 0.01);
        }
        this.z=1;
        weight=new Matrix(weightList,1);
    }
    public Neuron(int d,int z) {
        this.d = d;
        this.z = z;
        weightList=new ArrayList<>();
        for(int i=0;i<d*z;i++){
            weightList.add(new Random().nextGaussian() * 0.01);
        }
        weight=new Matrix(weightList,z);
    }
    public void print(){
        weight.print();
    }
    public Matrix impuls(Matrix x){
        return weight.multyply(x);
    }

    public void setWeightList(ArrayList<Double> weightList) {
        this.weightList = weightList;
        weight=new Matrix(weightList,z);
    }

    public Neuron duble(Neuron n){
        ArrayList<Double> weightList2=new ArrayList<>();
        for (int i = 0; i < n.weightList.size(); i++) {
            weightList2.add((n.weightList.get(i)+this.weightList.get(i))/2);
        }
        Neuron novi= new Neuron(d,z);
        novi.setWeightList(weightList2);
        return novi;
    }

}
