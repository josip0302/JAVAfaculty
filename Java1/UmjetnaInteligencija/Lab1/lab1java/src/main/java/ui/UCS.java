package ui;

import javax.print.attribute.standard.NumberUp;
import java.util.*;

import static java.lang.Thread.sleep;

public class UCS {
    String filename;
    Map<String,Map<String,Integer>> map;
    int statesV,n1,fullcount;
    List<String>path;
    Double cost;
    Map<Integer,List<String>> pathMap;
    Map<String,String>parentChild;
    String start,cilj;
    public UCS(String filename,String start){
        this.filename = filename;
        List<String> lista=new ArrayList<>();
        this.map=inputFormat.getMap(filename,lista);
        this.statesV=0;
        cost=0.0;
        path=new LinkedList<>();
        // pathMap=new HashMap<>();
        parentChild=new HashMap<>();
        n1=0;
        if(lista.size()>2){
            this.start=start.trim();
            this.cilj=lista.get(1);
            int x1=ucs(start,lista.get(1));
            this.statesV=0;
            cost=0.0;
            path.clear();

            parentChild.clear();
            n1=0;

            int x2=ucs(start,lista.get(2));

            if(x2>x1){
                this.cilj=lista.get(2);
                this.statesV=0;
                cost=0.0;
                path.clear();

                parentChild.clear();
                n1=0;
                ucs(start,lista.get(2));
            }
        }else {
            this.start=start;
            this.cilj=lista.get(1);
            ucs(start,cilj);

        }



    }
    public UCS(String filename) {
        this.filename = filename;
        List<String> lista=new ArrayList<>();
        this.map=inputFormat.getMap(filename,lista);
        this.statesV=0;
        cost=0.0;
        path=new LinkedList<>();
       // pathMap=new HashMap<>();
        parentChild=new HashMap<>();
        n1=0;
        if(lista.size()>2){
            start=lista.get(0);
            cilj=lista.get(1);
            int x1=ucs(lista.get(0),lista.get(1));
            this.statesV=0;
            cost=0.0;
            path.clear();

            parentChild.clear();
            n1=0;

            int x2=ucs(lista.get(0),lista.get(2));

            if(x2>x1){
                cilj=lista.get(2);
                this.statesV=0;
                cost=0.0;
                path.clear();

                parentChild.clear();
                n1=0;
                ucs(lista.get(0),lista.get(2));
            }
        }else {
            start=lista.get(0).trim();
            cilj=lista.get(1).trim();
            ucs(lista.get(0),lista.get(1).trim());

        }


    }



    public int ucs(String s0,String goal){
        List<String> open=new ArrayList<>();
        Set<String> visited=new TreeSet<>();
        open.add(s0+",0");
        int count=0;
        if(s0.equals(goal)){
            return 0;
        }
        while (!open.isEmpty()){

            String[] head=open.remove(0).split(",");
            String state=head[0];

          //  int n=Integer.parseInt(head[1]);

            visited.add(head[0]);
          //  n++;

           /*
            if(!pathMap.containsKey(n)) {
                pathMap.put(n,new ArrayList<>());
            }
            pathMap.get(n).add(state);
*/
            if(goal.equals(state)){
               // pathMap.get(n).removeAll(visited);
               // pathMap.get(n).add(state);

                statesV=visited.size();
                filterPath();
                return path.size();
            }else {

                Map<String,Integer> map1=map.get(state);
                for(String el:map1.keySet()){
                    String a=el + "," + map1.get(el);
                    if(!visited.contains(el)) {
                        insert(open, (a));

                        parentChild.put(el, state);
                    }
                   /* if(!visited.contains(el)) {
                        String s=el + "," + n;

                        if(!open.contains(s)) {
                            System.out.println(++count*1.0/fullcount + "%");
                            open.add(s);
                            parentChild.put(el,state);
                        }
                    }*/
                }
            }
        }
        n1=-1;

        return -1;
    }

    private void insert(List<String>open,String s) {
        int p=0;
        String[] s1= s.split(",");
        for(int i=open.size()-1;i>=0;i--){

            String[] s2= open.get(i).split(",");
            if(s1.length==2 && s2.length==2){
                if(Integer.parseInt(s1[1])>Integer.parseInt(s2[1])){
                    p=i;
                    break;
                }
            }
        }

        open.add(p, s);

    }
    private void countCost() {
        for (int i = 0;i<path.size()-1 ; i++) {
            String s=path.get(i);
            String s2=path.get(i+1);
            if(map.get(s)!=null) {
                try {
                    cost += map.get(s).get(s2);
                }catch (NullPointerException e) {

                }
            }
        }

    }
    public void filterPath() {




        String cilj=String.valueOf(this.start);

           /* if(pathMap.get(n1)!=null){
                s=pathMap.get(n1).get(0);
                cilj=pathMap.get(1).get(0);
            }*/
           /* for (int i = 1;i<=n1 ; i++) {
                List<String> ls = pathMap.get(i);
                if(ls!=null){
                    //       statesV+=ls.size();

                }
            }*/
            String s=this.cilj;

                while (!cilj.equals(s)) {

                    path.add(s);

                    s = parentChild.get(s);

                }

            path.add(cilj);
            statesV=parentChild.keySet().size()+1;
            Collections.reverse(path);
            n1=path.size();
            countCost();
        }


    public void print(){
        inputFormat.printFormat(n1,statesV,n1,cost,path);
    }
    public double getCount(){
        return this.cost;
    }
}
