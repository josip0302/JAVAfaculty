package hr.fer.zemris.java.tecaj_13.console;

import hr.fer.zemris.java.tecaj_13.hash.Util;
import hr.fer.zemris.java.tecaj_13.model.BlogComment;
import hr.fer.zemris.java.tecaj_13.model.BlogEntry;
import hr.fer.zemris.java.tecaj_13.model.BlogUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Add {
    public static void dodajKorisnika(EntityManagerFactory emf, BlogUser user){

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();


        em.persist(user);

        em.getTransaction().commit();
        em.close();
        System.out.println(user);


    }
    public static List<BlogUser> getUsers(EntityManagerFactory emf){

        EntityManager em = emf.createEntityManager();
        List<BlogUser> users=null;
     //  List<BlogUser> users= (List<BlogUser>) em.createQuery("from BlogUser").getResultList();
       em.close();
return users;




    }
    public static boolean validate(BlogUser user,String pass){
         return Util.encode(pass).equals(user.getPasswordHash());
    }
/*
    public static List<BlogEntry> getEntries(EntityManagerFactory emf,String s) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        BlogUser user= (BlogUser) em.createQuery("from BlogUser u where u.nick =:nick").setParameter("nick",s.trim()).getSingleResult();
em.close();
       return (List<BlogEntry>) user.getEntries();

    }*/
}
