import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

@WebListener
public class Inicijalizacija implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("daj bar se pokreni");
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		String connectionURL=null;
		try {
		InputStream inputStream = sce.getServletContext().getResourceAsStream("/WEB-INF/database.properties");
		Properties properties=new Properties();
		properties.load(inputStream);
		String dbName=properties.getProperty("name");
	   connectionURL = "jdbc:derby://"+properties.getProperty("host")+":"
			   +properties.getProperty("port")+"/"
			   + dbName + ";user="+properties.getProperty("user")+"" +
			   ";password="+properties.getProperty("password");



			cpds.setDriverClass("org.apache.derby.client.ClientAutoloadedDriver");

		} catch (PropertyVetoException e1) {
			throw new RuntimeException("Pogreška prilikom inicijalizacije poola.", e1);
		} catch (IOException e) {
			throw new RuntimeException("Pogreška prilkom učitavanja streama");
		}
		cpds.setJdbcUrl(connectionURL);

		sce.getServletContext().setAttribute("hr.fer.zemris.dbpool", cpds);

		try {
			Connection con=cpds.getConnection();
			ResultSet tables=	con.getMetaData().getTables(null,null,null,null);
			System.out.println(tables);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ComboPooledDataSource cpds = (ComboPooledDataSource)sce.getServletContext().getAttribute("hr.fer.zemris.dbpool");
		if(cpds!=null) {
			try {
				DataSources.destroy(cpds);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
