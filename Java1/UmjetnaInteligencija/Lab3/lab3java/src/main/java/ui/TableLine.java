package ui;

import java.util.*;

public class TableLine {
    List<String>columns;
    List<String> elements;
    Map<String,Set<String>> map=new HashMap<>();
    int lineLength;
    double da=0,ne=0;
    public TableLine(List<String> lines) {

        for(int i=0;i<lines.size();i++){
            String line=lines.get(i);
            if(i==0){
                List<String> stringList2=List.of(line.split(","));
                for(String l:stringList2){
                    map.put(l,new LinkedHashSet<>());
                }
                columns=new ArrayList<>(stringList2);
                elements=new ArrayList<>();
                lineLength=columns.size();
            }else {
                List<String> stringList=List.of(line.split(","));
                elements.addAll(stringList);

                for(int n=0;n<lineLength;n++){

                    map.get(columns.get(n)).add(stringList.get(n));
                }
                if(stringList.get(stringList.size()-1).equals("yes") || stringList.get(stringList.size()-1).equals("True")){
                    da+=1;
                }else if(stringList.get(stringList.size()-1).equals("no") || stringList.get(stringList.size()-1).equals("False")){
                    ne+=1;
                };
            }
        }
    }


    public void printTable() {
        for(int i =0;i<elements.size();i+=lineLength){
            System.out.print(i/lineLength +1 +".");
            for(int j = 0;j<lineLength;j++){
                System.out.print(" "+columns.get(j)+"="+elements.get(i+j));
            }
            System.out.println();
        }
    }

    public List<String> getLine(int l){
        List<String> res=new ArrayList<>();
        l--;
        for(int i=l*lineLength;i<(l+1)*lineLength;i++){
            res.add(elements.get(i));
        }
        return res;
    }

    public TableLine removeColumn(String column,String var){
        int x=columns.indexOf(column);

        Set<String> set=new LinkedHashSet<>();
        String a="";
        for(String s:columns){
            if(!s.equals(column)) {

                a += s + ",";
            }

        }
        set.add(a.substring(0,a.length()-1));
        for(int i=0;i<elements.size();i+=lineLength){
            String s="";
           if(elements.get(i+x).equals(var)) {
               for (int j = 0; j < lineLength; j++) {
                   if ((j) != (x)) {

                       s += elements.get(i + j) + ",";
                   }
               }
               set.add(s.substring(0, s.length() - 1));
           }

        }
        return new TableLine(new ArrayList<>(set));
    }

    public String getE(){
        return da+","+ne;
    }

    public double getEntrophy(){
        if(da ==0 || ne ==0)return 0;
        double nazivnik=da+ne;
        double prvi=da/nazivnik;
        double drugi=ne/nazivnik;
        double prviLog= (Math.log(prvi) / Math.log(2));
        double drugiLog= (Math.log(drugi) / Math.log(2));

        return -prvi*prviLog-drugi*drugiLog;
    }
    public double getSum(){
        return da+ne;
    }

    public double getIG(String column){
        double entorpy=this.getEntrophy();
        double nazivnik=getSum();
        for (String s:map.get(column)){

            TableLine t=removeColumn(column,s);

            entorpy-=(t.getSum()/nazivnik)*t.getEntrophy();
        }

        return entorpy;
    }

    public List<String> getColumns() {
        return columns;
    }
}
