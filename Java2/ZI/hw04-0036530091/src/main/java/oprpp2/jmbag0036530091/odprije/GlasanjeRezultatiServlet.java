package oprpp2.jmbag0036530091.odprije;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "GlasanjeRezultatiServlet", value = "/servleti/glasanje-rezultati")
public class GlasanjeRezultatiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ComboPooledDataSource cpds= (ComboPooledDataSource) getServletContext().getAttribute("hr.fer.zemris.dbpool");
        Map<String,String> map= new TreeMap<>();
        Map<String,String> Linkmap= new TreeMap<>();
        Map<String,String> map2= new TreeMap<>();
        Map<String,Integer> mapdis= new TreeMap<>();
        Map<String,Integer> mapraz= new TreeMap<>();
        LinkedHashMap<String,Integer> rez= new LinkedHashMap<>();
        Connection con = null;
        try {
            con = cpds.getConnection();
            String sql = "SELECT * FROM  POOLOPTIONS";
            PreparedStatement p = con.prepareStatement(sql);
            ResultSet s=  p.executeQuery();
            while (s.next()){
                map.put(s.getString(1),s.getString(2));
                map2.put(s.getString(1),s.getString(2));
                Linkmap.put(s.getString(2),s.getString(3));
                rez.put(s.getString(2),s.getInt(5));
                mapdis.put(s.getString(2),s.getInt(6));
                mapraz.put(s.getString(2),s.getInt(5)-s.getInt(6));
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*
        for(String s:list){
            String[] line=s.split("   ");
            map.put(line[0].strip(),line[1].strip());
            map2.put(line[0].strip(),line[1].strip());
            Linkmap.put(line[1].strip(),line[2].strip());
            rez.put(map2.get(s),Integer.parseInt(map.get(s)));
        }


        for(String s:list2){
            String[] line=s.split("   ");
            map2.put(line[0].strip(),line[1].strip());
            Linkmap.put(line[1].strip(),line[2].strip());

        }


        for(String s:map2.keySet()){

        }
      */
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
        request.setAttribute("dis",mapdis);
        request.setAttribute("raz",mapraz);
        request.getRequestDispatcher("/WEB-INF/servleti/glasanjeRez.jsp").forward(request, response);

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
