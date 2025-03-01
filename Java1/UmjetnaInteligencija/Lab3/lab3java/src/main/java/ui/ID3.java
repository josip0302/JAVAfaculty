package ui;


import java.util.*;

public class ID3 {
    TableLine table;
    int pp=0,pn=0,np=0,nn=0;
    Stablo stablo;
    Map<String,String> tree;
    String root=null;
    Set<String> branches;
    public ID3(TableLine table) {
        tree=new HashMap<>();
        branches=new LinkedHashSet<>();
        this.table = table;
    }

   String gotovo=null;
    boolean prva=false;
    public void fit(TableLine D,int level,String before,String forSet,int depth){

        if(level==depth){
            if(depth==0){
             prva=true;
            }
            String kraj=D.elements.get(D.elements.size()-1);
            if(D.da>D.ne){
                if(kraj.equals("True")||kraj.equals("False")){
                    kraj="True";
                }else {
                    kraj = "yes";
                }


            }else {

                if(kraj.equals("True")||kraj.equals("False")){
                    kraj="False";
                }else {
                    kraj="no";
                }

            }
            gotovo=kraj;
            if(forSet==null){
                forSet=kraj;
            }else {
                forSet += " " + kraj;
            }
            branches.add(forSet);
            tree.put(level+"#"+before,(level+1)+"#"+kraj);
            return;
        }

        if(D.getEntrophy()==0){
            String kraj=D.elements.get(D.lineLength-1);
            if(D.da>D.ne){
                if(kraj.equals("True")||kraj.equals("False")){
                    kraj="True";
                }else {
                    kraj = "yes";
                }


            }else {

                if(kraj.equals("True")||kraj.equals("False")){
                    kraj="False";
                }else {
                    kraj="no";
                }

            }
            forSet+=" "+kraj;
            branches.add(forSet);
            tree.put(level+"#"+before,(level+1)+"#"+kraj);
            return;
        }

        double max=0;
        String next=null;
        List<String> list=new ArrayList<>(D.columns);
        list.remove(list.size()-1);
        for (String s:list){

           double r=D.getIG(s);
            System.out.print("IG("+s+")="+r);
           if(r>max){
               max=r;
               next=s;
           }else if(r==max){
               int compare = next.compareTo(s);//a b

               if (compare > 0) {
                  next=s;

               }


           }


        }
        System.out.println();
        if(root==null){
            root=next;

        }else {
            if(before!=null){
                tree.put(level+"#"+before,(level+1)+"#"+next);
            }
        }
        if(forSet==null){
            forSet=(level+1)+":"+next+"=";
        }else {
            forSet+=" "+(level+1)+":"+next+"=";
        }


        for (String s:D.map.get(next)) {
            fit(D.removeColumn(next,s),level+1,s,forSet+s,depth);
        }

    }
    int correct;
    double accuracy;
    public void predict(String fileName){
        List<String> list= (List<String>) InAndOut.readCSVString(fileName);
        list.remove(0);
        correct=0;
        System.out.print("[PREDICTIONS]: ");
       for (String s:list){
           System.out.print(predictLine(s)+" ");
       }
        System.out.println();
       accuracy=(correct*1.0/list.size());

    }

    private String predictLine(String s) {
        List<String> list= List.of(s.split(","));
        if(gotovo!=null && prva){
            String predicted=gotovo;
            String actual=list.get(list.size()-1);
            if(predicted.equals("yes")||predicted.equals("True")){
                if(actual.equals("yes")||actual.equals("True")){
                    pp++;
                }else {
                    pn++;
                }
            }else {
                if(actual.equals("yes")||actual.equals("True")){
                    np++;
                }else {
                    nn++;
                }
            }

                if(gotovo.equals(list.get(list.size()-1))){
                    correct++;
                };
                return gotovo;

        }
        int index=table.columns.indexOf(root);

        int level=0;


        while (!list.isEmpty()){
            level++;
            String a=list.get(index);

            String predicted=tree.get(level+"#"+a).split("#")[1];

            String actual=list.get(list.size()-1);
            if(predicted.equals("yes")||predicted.equals("True")){
                if(actual.equals("yes")||actual.equals("True")){
                    pp++;
                }else {
                    pn++;
                }
            }else if(predicted.equals("no")||predicted.equals("False")) {
                if(actual.equals("yes")||actual.equals("True")){
                    np++;
                }else {
                    nn++;
                }
            }
            if(predicted.equals("yes") || predicted.equals("no")||predicted.equals("True") || predicted.equals("False")){
                if(predicted.equals(actual)){
                    correct++;
                };
                return predicted;
            }
            index=table.columns.indexOf(predicted);
        }

        return "no";
    }


    public void printBranches(){
        System.out.println("[BRANCHES]:");
        for(String s:branches){
            System.out.println(s);
        }


    }
    public void printAccuracy(){
        System.out.print("[ACCURACY]: ");
        Locale.setDefault(Locale.US);
        System.out.format("%.5f", accuracy);
        System.out.println();
    }

    public void matrix(){
        System.out.println("[CONFUSION_MATRIX]:");
        System.out.println(nn+" "+pn);
        System.out.println(np+" "+pp);
    }

}
