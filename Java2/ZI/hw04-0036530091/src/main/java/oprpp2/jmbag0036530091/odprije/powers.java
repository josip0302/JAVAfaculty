package oprpp2.jmbag0036530091.odprije;

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

@WebServlet(name = "powers", value = "/servleti/powers")
public class powers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String as = request.getParameter("ap");
        String bs = request.getParameter("bp");
        String ns = request.getParameter("np");
        int a = Integer.parseInt(as);
        int b = Integer.parseInt(bs);
        int n = Integer.parseInt(ns);


        String filename = "result.xls";
        HSSFWorkbook hwb = new HSSFWorkbook();
        for (int i = 1; i <= n; i++) {
            String t="sheet number " + i;
            HSSFSheet sheet = hwb.createSheet(t);
            int x = 0;
            for (int j = a; j <= b; j++) {
                HSSFRow rowhead = sheet.createRow((short) x);
                rowhead.createCell((short) 0).setCellValue(String.valueOf(j));
                rowhead.createCell((short) 1).setCellValue(String.valueOf(Math.pow(j, i)));
                x++;
            }


        }
        response.setHeader("Content-Disposition", "attachment; filename=\"tablica.xls\"");
        response.setContentType("application/vnd.ms-excel");
        OutputStream fileOut = response.getOutputStream();
        hwb.write(fileOut);
        fileOut.close();
        System.out.println("Your excel file has been generated!");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
