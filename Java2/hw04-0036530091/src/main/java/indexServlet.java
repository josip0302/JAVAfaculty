import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "indexSevlet", value = "/index-servlet")

public class indexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ide li");
      //  request.getRequestDispatcher("/WEB-INF/servleti/glasanjeRez.jsp").forward(request, response);
      //  response.sendRedirect("/servleti/index.html");
       // request.getRequestDispatcher("index.html").forward(request,response);
        ComboPooledDataSource cpds= (ComboPooledDataSource) getServletContext().getAttribute("hr.fer.zemris.dbpool");
        try {
            List<String> list=new ArrayList<>();
            Map<String,String> map1=new HashMap<>();
            Map<String,String> map2=new HashMap<>();
            Connection con=cpds.getConnection();
            String sql = "SELECT * FROM POOLS ";
            PreparedStatement p = con.prepareStatement(sql);

            ResultSet s=  p.executeQuery();
            while (s.next()){
                System.out.println(s.getString(1)+" "+s.getString(2)+" "+s.getString(3 ));
                list.add(s.getString(1));
                map1.put(s.getString(1),s.getString(2));
                map2.put(s.getString(1),s.getString(3));
            }
            System.out.println(list);
            request.setAttribute("list",list);
            request.setAttribute("map1",map1);
            request.setAttribute("map2",map2);
            System.out.println("Sve je dobro");
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.getRequestDispatcher("/WEB-INF/servleti/index.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
