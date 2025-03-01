package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class inputFormat {
    public static Map<String, Map<String,Integer>> getMap(String filename,List<String> startcilj){
        Map<String,Map<String,Integer>> map=new HashMap<>();
        try {
            List<String> lines= Files.readAllLines(Paths.get(filename));

            for(String line :lines){
              if(!line.startsWith("#")){
                  Map<String,Integer> miniMap=new LinkedHashMap<>();
                  if(line.contains(":")){
                      String[] mapline=line.split(":");

                    if(mapline.length==2) {
                        if (mapline[1].contains(",")) {
                            for (String s : mapline[1].split("\\s")) {
                                String[] a = s.split(",");
                                if (a.length == 2) {
                                    miniMap.put(a[0].trim(), Integer.parseInt(a[1]));
                                }
                            }
                        }
                    }
                      map.put(mapline[0],miniMap);
                  }else {

                      if (line.contains(" ")) {
                          startcilj.addAll(List.of(line.split("\\s")));
                      } else {
                          startcilj.add(line.trim());
                      }
                  }

              }

            }
            return map;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    static void printFormat(int FS,int statesV,int PathLength,double totalCost,List<String> path){
        if(FS==-1){
            System.out.println("[FOUND_SOLUTION]: no");
        }else {
            System.out.println("[FOUND_SOLUTION]: yes");
            System.out.println("[STATES_VISITED]: "+statesV);
            System.out.println("[PATH_LENGTH]: "+PathLength);
            System.out.println("[TOTAL_COST]: "+totalCost);
            System.out.print("[PATH]: ");
            for(String line:path){
                if(path.indexOf(line)!=path.size()-1) {
                    System.out.print(line + " => ");
                }else {
                    System.out.print(line);
                }
            }
        }
    }
}
