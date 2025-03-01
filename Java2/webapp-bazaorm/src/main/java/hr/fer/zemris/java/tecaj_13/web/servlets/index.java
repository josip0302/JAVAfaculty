package hr.fer.zemris.java.tecaj_13.web.servlets;

import hr.fer.zemris.java.tecaj_13.console.Add;
import hr.fer.zemris.java.tecaj_13.dao.DAOProvider;
import hr.fer.zemris.java.tecaj_13.model.BlogEntry;
import hr.fer.zemris.java.tecaj_13.model.BlogUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet("/servleti/main")
public class index extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("proslo ovuda");
        req.setAttribute("users",Add.getUsers((EntityManagerFactory) req.getServletContext().getAttribute("my.application.emf")));
        req.getRequestDispatcher("/WEB-INF/servleti/main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("sad je dobro");
        String nick=req.getParameter("nick");
        String pass=req.getParameter("pass");
        EntityManagerFactory emf=(EntityManagerFactory) req.getServletContext().getAttribute("my.application.emf");
                EntityManager em = emf.createEntityManager();
        BlogUser user= (BlogUser) em.createQuery("from BlogUser u where u.nick =:nick").setParameter("nick",nick).getSingleResult();
em.close();
        if(Add.validate(user,pass)){
            req.getSession().setAttribute("current.user.id",user.getId());
            req.getSession().setAttribute("current.user.fn",user.getId());
            req.getSession().setAttribute("current.user.ln",user.getId());
            req.getSession().setAttribute("current.user.nick",user.getId());
            System.out.println(user.getFirstName()+" se ulogirao");
        }
        req.getServletContext().getRequestDispatcher("/WEB-INF/servleti/main.jsp").forward(req, resp);
    }
}
