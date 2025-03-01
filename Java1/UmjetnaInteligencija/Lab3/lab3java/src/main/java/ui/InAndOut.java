package ui;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class InAndOut {


    public static TableLine readCSV(String fileName){
        Path pathToFile = Paths.get(fileName);
        List<String> lines=null;
        try {
            System.out.println(Path.of(fileName));
            lines= Files.readAllLines(Path.of(fileName));
            return new TableLine(lines);
        } catch (IOException e) {
            System.out.println(e);
        }
      return  null;
    }

    public static List<String> readCSVString(String fileName){
        Path pathToFile = Paths.get(fileName);
        List<String> lines=null;
        try {

            lines= Files.readAllLines(Path.of(fileName));
           return lines;
        } catch (IOException e) {
            System.out.println(e);
        }
        return  null;
    }

}
