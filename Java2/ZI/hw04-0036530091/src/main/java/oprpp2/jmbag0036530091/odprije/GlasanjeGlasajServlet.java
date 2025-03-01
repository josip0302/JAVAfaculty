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
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@WebServlet(name = "GlasanjeGlasajServlet", value = "/servleti/glasanje-glasaj")
public class GlasanjeGlasajServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ComboPooledDataSource cpds= (ComboPooledDataSource) getServletContext().getAttribute("hr.fer.zemris.dbpool");
        boolean like;
        String a=request.getParameter("like");
        if(a==null){
            a=request.getParameter("dis");
            like=false;
        }else {
            like=true;
        }
        Connection con = null;
        try {
            con = cpds.getConnection();
            String sql = "SELECT * FROM  POOLOPTIONS WHERE ID="+a;
            PreparedStatement p = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet s=  p.executeQuery();
            int colindex;
            if(like){
                colindex=5;
            }else {
                colindex=6;
            }
            if(s.next()) {
             int b = s.getInt(colindex) + 1;

            s.updateInt(colindex,b);
            s.updateRow();
                System.out.println("vrijednost postavljena na " + b);
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




;        response.sendRedirect(request.getContextPath() + "/glasanje-rezultati");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
