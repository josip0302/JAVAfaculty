package oprpp2.jmbag0036530091;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

@WebListener
public class Inicijalizacija implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		ComboPooledDataSource cpds = new ComboPooledDataSource();
		String connectionURL=null;

		try {
		InputStream inputStream = sce.getServletContext().getResourceAsStream("/WEB-INF/dbsettings.properties");
		Properties properties=new Properties();
		properties.load(inputStream);
		String dbName=properties.getProperty("name");
	    connectionURL = "jdbc:derby://"+properties.getProperty("host")+":"
			   +properties.getProperty("port")+"/"
			   + dbName + ";user="+properties.getProperty("user")+"" +
			   ";password="+properties.getProperty("password")+";";



			cpds.setDriverClass("org.apache.derby.client.ClientAutoloadedDriver");

		} catch (PropertyVetoException e1) {
			throw new RuntimeException("Pogreška prilikom inicijalizacije poola.", e1);
		} catch (IOException e) {
			throw new RuntimeException("Pogreška prilkom učitavanja streama");
		}

		System.out.println(connectionURL);
		cpds.setJdbcUrl(connectionURL);
		try {
			System.out.println("4");
			Connection con=cpds.getConnection();

			ResultSet tablePolls=	con.getMetaData().getTables(null,null,"POOLS",null);
			boolean polls=tablePolls.next();

			System.out.println(polls);
			if(!polls){
				System.out.println("pools");
				String sql = "CREATE TABLE POOLS"+
						"(ID BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "+
						"   TITLE VARCHAR(150) NOT NULL,  " +
						"  MESSAGE CLOB(2048) NOT NULL)";
				PreparedStatement p = con.prepareStatement(sql);
				System.out.println(3);
				 p.executeUpdate();

				tablePolls=	con.getMetaData().getTables(null,null,"POOLS",null);
				System.out.println(tablePolls.next());
			}
				System.out.println("poolsEmpty");
				String sql = "SELECT * FROM POOLS ";
				PreparedStatement p = con.prepareStatement(sql);

				ResultSet s=p.executeQuery();
				boolean b=s.next();
				if(!b){
					PreparedStatement pst=null;
					System.out.println("prazno polls");
					pst = con.prepareStatement(
							"INSERT INTO POOLS ( TITLE, MESSAGE) values (?,?)",
							Statement.RETURN_GENERATED_KEYS);

					pst.setString(1, "Glasanje za omiljeni bend:");
					pst.setString(2, "Od sljedećih bendova, koji Vam je bend najdraži? Kliknite na link kako biste glasali!");
                    int rows=pst.executeUpdate();
					System.out.println("Dodalo se "+rows+"redaka");
				}else {
					System.out.println("Poll title:"+s.getString(2));
				}


			ResultSet tablePollOPT=	con.getMetaData().getTables(null,null,"POOLOPTIONS",null);
			boolean pollsopt=tablePollOPT.next();

			System.out.println(pollsopt);
			if(!pollsopt){
				System.out.println("pollOpt");
				 sql = "CREATE TABLE POOLOPTIONS "+
						"   (id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,  "+
						"  optionTitle VARCHAR(100) NOT NULL,"+
						"    optionLink VARCHAR(150) NOT NULL, "+
						"   pollID BIGINT,    votesCount BIGINT, "+
						"   FOREIGN KEY (pollID) REFERENCES POOLS(ID))";
				 p = con.prepareStatement(sql);
				System.out.println(3);
				p.executeUpdate();
				System.out.println(2);
				tablePolls=	con.getMetaData().getTables(null,null,"POOLS",null);
				System.out.println(tablePolls.next());

			}
				System.out.println("poolsEmpty");
				sql = "SELECT * FROM POOLOPTIONS ";
				 p = con.prepareStatement(sql);

				 s=p.executeQuery();
				 b=s.next();
				if(!b){
					List<String> list=new ArrayList<>();
					list.add("1##The Beatles##https://www.youtube.com/watch?v=z9ypq6_5bsg##1##0");
					list.add("2##The Platters##https:  //www.youtube.com/watch?v=H2di83WAOhU##1##0");
					list.add("3##The Beach Boys##https://www.youtube.com/watch?v=2s4slliAtQU##1##0");
					list.add("4##The Four Seasons##https://www.youtube.com/watch?v=y8yvnqHmFds##1##0");
					list.add("5##The Marcels##https://www.youtube.com/watch?v=qoi3TH59ZEs##1##0");
					list.add("6##The Everly Brothers##https://www.youtube.com/watch?v=tbU3zdAgiX8##1##0");
					list.add("7##The Mamas And The Papas##https://www.youtube.com/watch?v=N-aK6JnyFmk##1##0");
					PreparedStatement pst=null;
					System.out.println("prazno polls");
					pst = con.prepareStatement(
							"INSERT INTO POOLOPTIONS ( OPTIONTITLE, OPTIONLINK,POLLID,VOTESCOUNT) values (?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
					for(String a:list) {

						String[] A=a.split("##");
						System.out.println(A[1]);
						pst.setString(1, A[1]);
						pst.setString(2, A[2]);
						pst.setInt(3, Integer.parseInt(A[3]));
						pst.setInt(4, Integer.parseInt(A[4]));
						int rows = pst.executeUpdate();
						System.out.println("Dodalo se " + rows + "redaka");
						System.out.println("prazno options");
					}

				}else {
					System.out.println(s.getString(2));

				}

			con.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}



		sce.getServletContext().setAttribute("hr.fer.zemris.dbpool", cpds);

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
