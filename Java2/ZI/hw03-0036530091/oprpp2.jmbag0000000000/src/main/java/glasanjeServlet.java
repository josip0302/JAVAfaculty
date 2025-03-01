import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@WebServlet(name = "glasanjeServlet", value = "/glasanje")
public class glasanjeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getServletContext().getRealPath("/WEB-INF/glasanje-definicija.txt");
        List<String> list= Files.readAllLines(Path.of(fileName));
        Map<String,String> map= new TreeMap<>();
        for(String s:list){
            String[] line=s.split("   ");
            map.put(line[0],line[1]);

        }
        request.setAttribute("mapaBendova",map);
        request.getRequestDispatcher("/WEB-INF/pages/glasanjeIndex.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
