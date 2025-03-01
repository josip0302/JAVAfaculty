package oprpp2.jmbag0036530091.odprije;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@WebServlet(name = "glasanje-xls", value = "/servleti/glasanje-xls")
public class glasanjexls extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = "result.xls";
        HSSFWorkbook hwb = new HSSFWorkbook();
        /*
        String fileName = request.getServletContext().getRealPath("/WEB-INF/glasanje-rezultati.txt");
        List<String> list= Files.readAllLines(Path.of(fileName));
        Map<String,String> map= new TreeMap<>();
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

        }*/
        String t="sheet number";
        HSSFSheet sheet = hwb.createSheet(t);
        int x=0;
        /*for(String s:map2.keySet()){
            HSSFRow rowhead = sheet.createRow((short) x);
            rowhead.createCell((short) 0).setCellValue(map2.get(s));
            rowhead.createCell((short) 1).setCellValue(map.get(s));
            x++;
        }*/
        ComboPooledDataSource cpds= (ComboPooledDataSource) getServletContext().getAttribute("hr.fer.zemris.dbpool");

        Connection con = null;
        try {
            con = cpds.getConnection();
            String sql = "SELECT * FROM  POOLOPTIONS";
            PreparedStatement p = con.prepareStatement(sql);
            ResultSet s=  p.executeQuery();
            while (s.next()){
                HSSFRow rowhead = sheet.createRow((short) x);
                rowhead.createCell((short) 0).setCellValue(s.getString(2));
                rowhead.createCell((short) 1).setCellValue(s.getInt(5));
                x++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.setHeader("Content-Disposition", "attachment; filename=\"glasanje.xls\"");
        response.setContentType("application/vnd.ms-excel");
        OutputStream fileOut = response.getOutputStream();
        hwb.write(fileOut);
        fileOut.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
