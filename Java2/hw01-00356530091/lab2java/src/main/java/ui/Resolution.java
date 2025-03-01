package ui;

import java.util.*;

public class Resolution {

String filname;
List<Proposition>data;
    public Resolution(String fileName) {
        this.filname=fileName;
        data=new LinkedList<>();
        Input.getData(data,fileName);

        Proposition goal=data.get(data.size()-1);
        data.remove(data.size()-1);

        boolean rez=algorithm(data,goal);
        if(rez){
            System.out.println("[CONCLUSION]: "+goal+" is true");
        }else {
            System.out.println("[CONCLUSION]: "+goal+" is unknown");
        }
    }
    public Resolution(String fileName,List<String> commands) {
        this.filname=fileName;
        data=new LinkedList<>();
        Input.getData(data,fileName);
        for (String command:commands) {
            System.out.println("User’s command:"+command);
            if (command.contains("?")) {

                Proposition goal = new Proposition(command.replace("?","").trim());
                boolean rez = algorithm(data, goal);
                if (rez) {
                    System.out.println("[CONCLUSION]: " + goal + " is true");
                } else {
                    System.out.println("[CONCLUSION]: " + goal + " is unknown");
                }
            } else if (command.contains("-")) {
                Proposition goal = new Proposition(command.replace("-","").trim());
                data.remove(goal);
                System.out.println("removed "+goal);

            } else if (command.contains("+")) {
                Proposition goal = new Proposition(command.replace("+","").trim());
                data.add(goal);
                System.out.println("Added "+goal);
            }
        }
    }


    public boolean algorithm(List<Proposition> F,Proposition G){
        List<Proposition> clauses=F;
        List<Proposition> sos=new ArrayList<>();
        sos.add(G.complement());
        List<Proposition> remove=new ArrayList<>();
        int i=1;
        for (Proposition p:clauses){
            if(p.isTauntology()){
                remove.add(p);

            }else {
               p.setIndex(i);
               i++;
                System.out.println(p);
            }
        }
        sos.get(0).setIndex(i);
        System.out.println(sos.get(0));
        clauses.removeAll(remove);
        remove.clear();
        List<Proposition> neW=new ArrayList<>();
        int index=0;
        int counter=clauses.size()+2;
        int index2=counter;
        System.out.println("=".repeat(15));
        while (true){
             remove=new ArrayList<>();
            for (Proposition p:sos){
                if(p.isTauntology()){
                   remove.add(p);
                }
            }
            sos.removeAll(remove);
        if(sos.size()<=index){
            printSos(sos, counter);
         return false;
       }
            Proposition s=sos.get(index);
           for(Proposition p:clauses){
               Proposition a=p.resolve(s);

               if(a!=null){
                   a.setIndex(index2++);
                   neW.add(a);

                   if(a.NIL()){
                       sos.add(a);
                       printSosNil(sos,counter);
                      //  printSos(sos,counter);
                       return true;
                   }
               }
           }
           for(Proposition p:neW){
               if(clauses.contains(p)){
                   printSos(sos, counter);
                   return false;}

           }
           List<Proposition> prijenos=new ArrayList<>();
           for (Proposition p:neW){
               boolean dodaj=true;
               for (Proposition s1:sos){
                   if(s1.jePodskup(p)){
                      dodaj=false;
                   }
               }
               if(dodaj){
                   prijenos.add(p);
               }
           }
           sos.addAll(prijenos);
           neW.clear();
           prijenos.clear();
           index++;

        }

    }

    private void printSosNil(List<Proposition> sos, int counter) {
        List<Proposition> list=new ArrayList<>();
        list.add(sos.get(sos.size()-1));
        int next=list.get(0).getOrigin2();
        while (next>=(counter)){
            Proposition p=sos.get(next-counter+1);
            list.add(p);
            next=p.getOrigin2();
        }

        Collections.reverse(list);

        printSos2(list,counter);
    }

    private void printSos(List<Proposition> sos, int counter) {

        for(int i=1;i<sos.size();i++){
            sos.get(i).setIndex(counter++);
            System.out.println(sos.get(i));
        }
        System.out.println("=".repeat(15));
    }
    private void printSos2(List<Proposition> sos, int counter) {

        for(int i=0;i<sos.size();i++){
            sos.get(i).setIndex(counter++);

        }
        for(int i = 1;i<sos.size();i++){
            sos.get(i).setOrigin2(sos.get(i-1).getIndex());
        }
        for(int i=0;i<sos.size();i++){
            System.out.println(sos.get(i));
        }
        System.out.println("=".repeat(15));
    }


}
