package hr.fer.zemris.java.tecaj_13.web.servlets;

import hr.fer.zemris.java.tecaj_13.console.Add;
import hr.fer.zemris.java.tecaj_13.model.BlogEntry;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/servleti/author/*")
public class author extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String info=req.getPathInfo();
        if(info!=null){
            String[] polje=info.split("/");
            if(polje.length==1){

                //List<BlogEntry> entries=Add.getEntries((EntityManagerFactory) req.getServletContext().getAttribute("my.application.emf"),polje[0]);
               // req.setAttribute("entries",entries);
              //  req.getRequestDispatcher("/WEB-INF/servleti/author/NICK.jsp").forward(req, resp);
            } else if (polje.length==2) {

            }
        }
       req.getRequestDispatcher("/WEB-INF/servleti/author/NICK.jsp").forward(req, resp);
    }
}
