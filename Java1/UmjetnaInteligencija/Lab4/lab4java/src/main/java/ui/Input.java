package ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Input {
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

    public static List<NeuralNet> evaluate(List<NeuralNet>list, int el){
        List<NeuralNet> rez=new ArrayList<>();
        for (int j = 0; j < el; j++) {
            double error=10;
            NeuralNet r=null;
            for (int i = 0; i < list.size(); i++) {
                NeuralNet net = list.get(i);
                net.start();
                Double err = net.error;
                if(error>err){
                    error=err;
                    r=net;
                }


            }
            list.remove(r);
            rez.add(r);
        }

        return rez;
    }
}
