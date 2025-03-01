package ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Input {

    public static void getData(List list,String fileName){

        try {
            for (String line : Files.readAllLines(Path.of(fileName))) {
                if(!line.startsWith("#")) {
                    list.add(new Proposition(line.toLowerCase()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getInput(String fileName){
        List<String> list=new ArrayList<>();
        try {
            for (String line : Files.readAllLines(Path.of(fileName))) {
                if(!line.startsWith("#")) {
                    list.add(line.toLowerCase());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
return  list;
    }
}
