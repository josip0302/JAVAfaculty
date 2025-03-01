package hr.fer.zemris.java.tecaj_13.web.servlets;

import hr.fer.zemris.java.tecaj_13.console.Add;
import hr.fer.zemris.java.tecaj_13.hash.Util;
import hr.fer.zemris.java.tecaj_13.model.BlogEntry;
import hr.fer.zemris.java.tecaj_13.model.BlogUser;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet("/servleti/register")
public class register extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("proslo ovuda");
        req.getRequestDispatcher("/WEB-INF/servleti/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName=req.getParameter("firstName");
        String lastName=req.getParameter("lastName");
        String email=req.getParameter("email");
        String nick=req.getParameter("nick");
        String pass=req.getParameter("pass");
        String hashpass=Util.encode(pass);
        BlogUser user=new BlogUser();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setNick(nick);
        user.setPasswordHash(hashpass);
        Add.dodajKorisnika((EntityManagerFactory) req.getServletContext().getAttribute("my.application.emf"),user);

        req.getServletContext().getRequestDispatcher("/WEB-INF/servleti/main.jsp").forward(req, resp);
    }


}
