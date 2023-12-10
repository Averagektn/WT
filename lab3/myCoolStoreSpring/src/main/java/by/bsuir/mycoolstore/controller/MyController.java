package by.bsuir.mycoolstore.controller;

import by.bsuir.mycoolstore.entity.UserEntity;
import by.bsuir.mycoolstore.entity.enums.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

@WebServlet(name = "Servlet", value = "/Servlet")
public class MyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserEntity user = new UserEntity();
        user.setUsrEmail("testedusasdr@mail");
        user.setUsrPassword("atested");
        user.setUsrRole(Role.CUSTOMER.toString());
        user.setUsrBannedBy(null);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myCoolStoreSpring");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(user);
            transaction.commit();
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("New user created successfully.");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error creating new user.");
        } finally {
            em.close();
            emf.close();
        }
    }
}
