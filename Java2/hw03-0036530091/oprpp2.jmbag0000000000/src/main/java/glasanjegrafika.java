import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@WebServlet(name = "glasanje-grafika", value = "/glasanje-grafika")
public class glasanjegrafika extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DefaultPieDataset result = new DefaultPieDataset();

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

        }


        for(String s:map2.keySet()){
            result.setValue(map2.get(s),Integer.parseInt(map.get(s)));

        }

        JFreeChart chart = ChartFactory.createPieChart3D(
                "OS usage",                  // chart title
                result,                // data
                true,                   // include legend
                true,
                false
        );
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setForegroundAlpha(0.5f);
        plot.setDirection(Rotation.CLOCKWISE);
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(200, 200));
        response.setContentType("image/png");

        OutputStream outputStream = response.getOutputStream();


        int width = 500;
        int height = 350;
        File f = new File("slika.png");
        ChartUtilities.writeChartAsPNG(outputStream, chart, width, height);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
