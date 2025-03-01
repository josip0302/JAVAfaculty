package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Heuristics {
    public static void checkConsist(String filename, String checkFile) {
        List<String> lista=new ArrayList<>();
        Map<String, Map<String,Integer>> map=inputFormat.getMapSorted(filename,lista);

        Map<String,Double> mapH=inputFormat.getH(checkFile);
        System.out.println("# HEURISTIC-CONSISTENT "+checkFile);
        boolean ok=true;
        for(String el:mapH.keySet()){

            double h=mapH.get(el);
            Map<String,Integer>map2=map.get(el);
            if(map2!=null){
                for(String el2: map2.keySet()){
                    double a=mapH.get(el2);
                    double b=map2.get(el2);
                    System.out.print("[CONDITION]: ");
                    if(h<=a+b){
                        System.out.print("[OK] ");
                    }else {
                        System.out.print("[ERR] ");
                        ok=false;
                    }
                    System.out.println("h("+el+") <= h("+el2+") + c: "+h+" <= "+a+" + "+b);;
                }
            }
        }

        System.out.print("[CONCLUSION]: Heuristic is ");
        if(!ok){
            System.out.print("not ");
        }
        System.out.println("consistent.");
    }
    public static void checkOpt(String filename, String checkFile) {
        Map<String,Double> mapH=inputFormat.getH(checkFile);
        System.out.println("# HEURISTIC-OPTIMISTIC "+checkFile);
        boolean ok=true;
        for(String el:mapH.keySet()){
            UCS ucs=new UCS(filename,el);
            double a =mapH.get(el);
            double b=ucs.getCount();
            System.out.print("[CONDITION]: ");
            System.out.print(a<=b?"[OK] ":"[ERR] ");
            if(a>b){
                ok=false;
            }
            System.out.println("h("+el+") <= h*: "+a+" <= "+b );
            ucs=null;
        }
        System.out.print("[CONCLUSION]: Heuristic is ");
        if(!ok){
            System.out.print("not ");
        }
        System.out.println("optimistic.");
    }

}
