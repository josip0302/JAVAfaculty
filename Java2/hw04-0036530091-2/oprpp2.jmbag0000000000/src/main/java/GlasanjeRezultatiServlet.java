

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@WebServlet(name = "GlasanjeRezultatiServlet", value = "/glasanje-rezultati")
public class GlasanjeRezultatiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getServletContext().getRealPath("/WEB-INF/glasanje-rezultati.txt");
        List<String> list= Files.readAllLines(Path.of(fileName));
        Map<String,String> map= new TreeMap<>();
        Map<String,String> Linkmap= new TreeMap<>();
        for(String s:list){
            String[] line=s.split("   ");
            map.put(line[0].strip(),line[1].strip());


        }

        String fileName2 = request.getServletContext().getRealPath("/WEB-INF/glasanje-definicija.txt");
        List<String> list2= Files.readAllLines(Path.of(fileName2));
        Map<String,String> map2= new TreeMap<>();
        for(String s:list2){
            String[] line=s.split("   ");
            map2.put(line[0].strip(),line[1].strip());
            Linkmap.put(line[1].strip(),line[2].strip());

        }

        LinkedHashMap<String,Integer> rez= new LinkedHashMap<>();
        for(String s:map2.keySet()){
            rez.put(map2.get(s),Integer.parseInt(map.get(s)));
        }
      /*  ArrayList<String> list3 = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : rez.entrySet()) {
            list3.add(String.valueOf(entry.getValue()));
        }
        Collections.sort(list3, new Comparator<String>() {
            public int compare(String str, String str1) {
                return (str).compareTo(str1);
            }
        });
        for (String str : list) {
            for (Map.Entry<String, Integer> entry : rez.entrySet()) {
                if (entry.getValue().equals(str)) {
                    rez.put(entry.getKey(), Integer.parseInt(str));
                }
            }
        }*/
        rez=sortMap(rez);
        int count=1;
        for(String s:rez.keySet()){
            if(count==3)break;
            request.setAttribute("bandNumber"+count,s);
            request.setAttribute("linkNumber"+count,Linkmap.get(s));
            count++;

        }
        System.out.println(rez);
        request.setAttribute("rez",rez);
        request.getRequestDispatcher("/WEB-INF/pages/glasanjeRez.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    // function copied from https://www.programiz.com/java-programming/examples/sort-map-values
    public static LinkedHashMap sortMap(LinkedHashMap map) {
        List <Map.Entry<String, Integer>> capitalList = new LinkedList<>(map.entrySet());

        // call the sort() method of Collections
        Collections.sort(capitalList, (l1, l2) -> l2.getValue() - l1.getValue());

        // create a new map
        LinkedHashMap<String, Integer> result = new LinkedHashMap();

        // get entry from list to the map
        for (Map.Entry<String, Integer> entry : capitalList) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
}
}
