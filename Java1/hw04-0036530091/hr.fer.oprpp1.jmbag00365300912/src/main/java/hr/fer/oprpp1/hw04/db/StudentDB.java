package hr.fer.oprpp1.hw04.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudentDB {
    public static void main(String[] args) {
        while (true) {
            try {
                List<String> lines = Files.readAllLines(
                        Paths.get("src/main/resources/database.txt"),
                        StandardCharsets.UTF_8
                );
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in));
                StudentDatabase db = new StudentDatabase(lines);
                String query =reader.readLine();
                List<String> showing=new ArrayList<>();
                if(query.length()==0)continue;
                QueryParser parser = new QueryParser(query);
                int counter = 0;
                System.out.println(">" + query);
                if(query.contains("showing")){
                    String[] s=query.split("showing");
                    if(s.length==1){

                        throw new IOException(" SHOWING bez elemenata iza");
                    } else if (s[1].trim().equals("")) {
                        throw new IOException(" SHOWING bez elemenata iza");
                    }else if(!s[1].contains("lastName")&&!s[1].contains("firstName")&&!s[1].contains("jmbag")&&!s[1].contains("finalGrade")){
                        throw new IOException(" SHOWING s krivim elementima iza");
                    }
                    query=query.split("showing")[0];
                    if(query.length()==0)continue;
                    for(String s1:s[1].split(",")) {
                        showing.add(s1.trim());
                    }
                }
                if(query.equals("exit"))break;
                List<StudentRecord> records = new ArrayList<>();
                if (parser.isDirectQuery()) {
                    StudentRecord r = db.forJMBAG(parser.getQueriedJMBAG());
                    System.out.println("Using index for record retrieval.");
                    records.add(r);
                    counter++;
                } else {
                    for (StudentRecord r : db.filter(new QueryFilter(parser.getQuery()))) {
                        records.add(r);
                        counter++;
                    }
                }

                List<String> output = RecordFormatter.format(records,showing);
                output.forEach(System.out::println);
                System.out.println("Records selected:" + counter);
            } catch (IOException e) {
                System.out.println("greska:"+e.toString());
            }
        }
        System.out.println("Goodbye!");
    }
}
