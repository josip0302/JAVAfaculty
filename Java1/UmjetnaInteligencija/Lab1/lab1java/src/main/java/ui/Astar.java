package ui;

import java.util.*;

public class Astar {
    String filename;
    Map<String,Map<String,Integer>> map;
    Map<String,Double>maph;
    int statesV,n1,fullcount;
    List<String>path;
    Double cost;
    Map<Integer,List<String>> pathMap;
    Map<String,String>parentChild;
    String start,cilj;

    public Astar(String filename, String h) {
        this.filename = filename;
        List<String> lista=new ArrayList<>();
        this.map=inputFormat.getMap(filename,lista);
        maph=inputFormat.getH(h);
        this.statesV=0;
         cost = 0.0;
        path=new LinkedList<>();
        // pathMap=new HashMap<>();
        parentChild=new HashMap<>();
        n1=0;
        if(lista.size()>2){
            start=lista.get(0);
            cilj=lista.get(1);
            int x1=astar(lista.get(0),lista.get(1));
            this.statesV=0;
            cost=0.0;
            path.clear();

            parentChild.clear();
            n1=0;

            int x2=astar(lista.get(0),lista.get(2));

            if(x2>x1){
                cilj=lista.get(2);
                this.statesV=0;
                cost=0.0;
                path.clear();

                parentChild.clear();
                n1=0;
                astar(lista.get(0),lista.get(2));
            }
        }else {
            start=lista.get(0).trim();
            cilj=lista.get(1).trim();
            astar(lista.get(0),lista.get(1).trim());

        }

    }
    public int astar(String s0,String goal){
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
            visited.add(head[0]);

            if(goal.equals(state)){

                statesV=visited.size();
                filterPath();
                return path.size();
            }else {

                Map<String,Integer> map1=map.get(state);
                for(String el:map1.keySet()){
                    String a=el + "," + map1.get(el);
                    if(!visited.contains(el)) {
                        insert(open, (el+","+maph.get(el)));

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


        return -1;
    }
    private void insert(List<String>open,String s) {
        int p=0;
        String[] s1= s.split(",");
        for(int i=open.size()-1;i>=0;i--){

            String[] s2= open.get(i).split(",");
            if(s1.length==2 && s2.length==2){
                if(Double.parseDouble(s1[1])>Double.parseDouble(s2[1])){
                    p=i;
                    break;
                }
            }
        }

        open.add(p, s);

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
        System.out.println(cilj);
        System.out.println(parentChild);
        while (!cilj.equals(s)) {
            System.out.println(s);
            path.add(s);

            s = parentChild.get(s);

        }

        path.add(cilj);
        statesV=parentChild.keySet().size()+1;
        Collections.reverse(path);
        n1=path.size();
        countCost();
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
    public void print(){
        inputFormat.printFormat(n1,statesV,n1,cost,path);
    }

}
