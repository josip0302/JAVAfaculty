import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "vrijeme", value = "/vrijeme")
public class vrijeme extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url="https://www.fer.unizg.hr/_news/icons/a1580ff3a85ebd78506b23ae43a064235410_icon.jpg";
        String url2="https://www.fer.unizg.hr/lib/generic_theme/images/FER_logo_1.svg";
        Random rand = new Random();
        int a= rand.nextInt(2);
        if(request.getParameter("number")!=null){
            a= Integer.parseInt(request.getParameter("number"));
        }

        if(a==0){
            request.setAttribute("slika",url);
        }else {
            request.setAttribute("slika",url2);
        }
        request.getRequestDispatcher("/WEB-INF/pages/vrijeme.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
