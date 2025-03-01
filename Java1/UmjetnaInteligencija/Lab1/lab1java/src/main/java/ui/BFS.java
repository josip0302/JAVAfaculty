package ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class BFS {
    String filename;
    Map<String,Map<String,Integer>> map;
    int statesV,n1,fullcount;
    List<String>path;
    Double cost;
    Map<Integer,List<String>> pathMap;
    Map<String,String>parentChild;
    public BFS(String filename) {
        this.filename = filename;
        List<String> lista=new ArrayList<>();
        this.map=inputFormat.getMap(filename,lista);
        this.statesV=0;
        cost=0.0;
        path=new ArrayList<>();
        pathMap=new HashMap<>();
        parentChild=new HashMap<>();
        n1=0;
        if(lista.size()>2){
            int x1=bfs(lista.get(0),lista.get(1));
            this.statesV=0;
            cost=0.0;
            path=new ArrayList<>();
            pathMap=new HashMap<>();
            parentChild=new HashMap<>();
            n1=0;

            int x2=bfs(lista.get(0),lista.get(2));

            if(x2>x1){
                this.statesV=0;
                cost=0.0;
                path=new ArrayList<>();
                pathMap=new HashMap<>();
                parentChild=new HashMap<>();
                n1=0;
                bfs(lista.get(0),lista.get(2));
            }
        }else {
            this.statesV=0;
            cost=0.0;
            path=new ArrayList<>();
            pathMap=new HashMap<>();
            parentChild=new HashMap<>();
            n1=0;
            fullcount=map.keySet().size();
            bfs(lista.get(0),lista.get(1).trim());
        }

        filterPath();
        print();
    }
    public int bfs(String s0,String goal){
        List<String> open=new ArrayList<>();
        List<String> visited=new ArrayList<>();
        open.add(s0+",0");
       int count=0;

        while (!open.isEmpty()){

            String[] head=open.remove(0).split(",");
            String state=head[0];

            int n=Integer.parseInt(head[1]);
            visited.add(state);
            n++;

            if(!pathMap.containsKey(n)) {
                pathMap.put(n,new ArrayList<>());
            }
            pathMap.get(n).add(state);

            if(goal.equals(state)){
               pathMap.get(n).removeAll(visited);
               pathMap.get(n).add(state);
               statesV=visited.size();
                n1=n;
                return n;
            }else {


                for(String el:map.get(state).keySet()){
                    if(!visited.contains(el)) {
                        String s=el + "," + n;

                        if(!open.contains(s)) {

                            open.add(s);
                           parentChild.put(el,state);
                        }
                    }
                }
            }
        }
        n1=-1;

        return -1;
    }

    public void filterPath() {

        String s="";
        String cilj="";
        if(n1!=-1) {
            if(pathMap.get(n1)!=null){
                s=pathMap.get(n1).get(0);
                cilj=pathMap.get(1).get(0);
            }
            for (int i = 1;i<=n1 ; i++) {
                List<String> ls = pathMap.get(i);
                if(ls!=null){
             //       statesV+=ls.size();

                }
            }
            while (!s.equals(cilj)){
                path.add(s);
                s=parentChild.get(s);
            }
            path.add(cilj);
            Collections.reverse(path);
            countCost();
        }

    }

    private void countCost() {
        for (int i = 0;i<n1-1 ; i++) {
            String s=path.get(i);
            String s2=path.get(i+1);
            if(map.get(s)!=null) {
                cost += map.get(s).get(s2);
            }
        }

    }


    public void print(){
        inputFormat.printFormat(n1,statesV,n1,cost,path);
    }


}
