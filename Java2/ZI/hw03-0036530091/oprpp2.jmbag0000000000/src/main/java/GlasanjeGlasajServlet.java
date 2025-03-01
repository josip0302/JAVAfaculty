import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@WebServlet(name = "GlasanjeGlasajServlet", value = "/glasanje-glasaj")
public class GlasanjeGlasajServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getServletContext().getRealPath("/WEB-INF/glasanje-rezultati.txt");
        List<String> list= Files.readAllLines(Path.of(fileName));
        Map<String,String> map= new TreeMap<>();
        for(String s:list){
            String[] line=s.split("   ");
            map.put(line[0],line[1]);

        }
        String a=request.getParameter("id");
        int b=Integer.parseInt(map.get(a).strip());
        b++;
        System.out.println(b);
        map.put(a,String.valueOf(b));
        try {
            Path filePath = Paths.get(fileName);
            Files.deleteIfExists(filePath);
            Files.createFile(filePath);


            for(String key: map.keySet()) {
                String word=key+"   "+map.get(key);
                System.out.println(word);
                Files.writeString(filePath, word + System.lineSeparator(),
                        StandardOpenOption.APPEND);
                //REMOVE THE BELOW
                //writer.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Saving to a text file failed...");
        }
;        response.sendRedirect(request.getContextPath() + "/glasanje-rezultati");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
