package ui;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
    ArrayList<ArrayList<Double>> list;
    int columns;
    int rows;

    public Matrix(ArrayList<Double> list, int columns) {
        rows=list.size()/columns;
        this.list = new ArrayList<>();

        for(int i =0;i<list.size();i+=columns){
            ArrayList<Double> help=new ArrayList<>();
            for(int j =0;j<columns;j++){
                help.add(list.get(i+j));
            }
            this.list.add(help);
        }

        this.columns = columns;
    }

    public ArrayList<Double> getRow(int i){
        return list.get(i);
    }
    public ArrayList<Double> getColumn(int i){
        ArrayList<Double> rez=new ArrayList<>();
        for(List list1:list){
            rez.add((Double) list1.get(i));
        }
        return rez;
    }


    public void print() {
        for(int i =0;i<rows;i++){
            for(int j =0;j<columns;j++){
               System.out.print(list.get(i).get(j)+" ");
           }
           System.out.println();
       }
    }
    public Matrix multyply(Matrix other){
        ArrayList<Double> rez=new ArrayList<>();
        int d= other.columns;
        for(int i=0;i<this.rows;i++){
            ArrayList<Double> row=this.getRow(i);
            for(int j=0;j<other.columns;j++){
                ArrayList<Double> col=other.getColumn(j);
                Double r=0.0;
                for (int a=0;a<col.size();a++){
                    r+=row.get(a)*col.get(a);
                }
                rez.add(r);
            }
        }
        return new Matrix(rez,d);
    }
    public double getFirst(){
        return list.get(0).get(0);
    }
    public void sigmoid(){
        for(int i =0;i<rows;i++){
            for(int j =0;j<columns;j++){
               double x=list.get(i).remove(j);
               list.get(i).add(j,1/(1+Math.exp(-x)));
            }

        }
    }

}
