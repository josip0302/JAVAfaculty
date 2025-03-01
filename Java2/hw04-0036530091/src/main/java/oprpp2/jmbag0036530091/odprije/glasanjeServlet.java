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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@WebServlet(name = "glasanjeServlet", value = "/glasanje")
public class glasanjeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ComboPooledDataSource cpds= (ComboPooledDataSource) getServletContext().getAttribute("hr.fer.zemris.dbpool");
        Map<String,String> map= new TreeMap<>();
        Connection con= null;
        String a=request.getParameter("pollId");
        try {
            con = cpds.getConnection();
            String sqlp = "SELECT * FROM  POOLS WHERE ID="+a;
            PreparedStatement p1 = con.prepareStatement(sqlp);

            ResultSet s=  p1.executeQuery();
            if(s.next()){
                request.setAttribute("naslov",s.getString(2));
                request.setAttribute("poruka",s.getString(3));
            }

            String sql = "SELECT * FROM  POOLOPTIONS WHERE POLLID="+a;
            PreparedStatement p = con.prepareStatement(sql);

            s=  p.executeQuery();
            while (s.next()){

                    map.put(s.getString(1),s.getString(2));


            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        request.setAttribute("mapaBendova",map);
        request.getRequestDispatcher("/WEB-INF/servleti/glasanjeIndex.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
