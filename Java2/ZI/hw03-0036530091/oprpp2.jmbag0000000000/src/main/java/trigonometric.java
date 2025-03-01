import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@WebServlet(name = "trigonometric", value = "/trigonometric")
public class trigonometric extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String as=request.getParameter("a");
        String bs=request.getParameter("b");
        int a= as==null?0:Integer.parseInt(as);
        int b= bs==null?360:Integer.parseInt(bs);
        if(a>b){
            int pom;
            pom=a;
            a=b;
            b=pom;
        }
        if(b>a+720){
            b=a+720;
        }
        List<String> list=new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.#######");
        for(int i=a;i<=b;i++){

            list.add("Sin("+i+") = "+df.format(Math.sin(Math.toRadians(i)))+" - Cos("+i+") = "+df.format(Math.cos(Math.toRadians(i))));
        }

       request.setAttribute("list",list);
        request.getRequestDispatcher("/WEB-INF/pages/trigonometric.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
