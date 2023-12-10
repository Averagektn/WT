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

        // Создание EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myCoolStoreSpring");

        // Создание EntityManager
        EntityManager em = emf.createEntityManager();

        // Создание транзакции
        EntityTransaction transaction = em.getTransaction();

        try {
            // Начало транзакции
            transaction.begin();

            // Сохранение нового пользователя в базе данных
            em.persist(user);

            // Фиксация транзакции
            transaction.commit();

            // Установка статуса успешного выполнения операции
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("New user created successfully.");
        } catch (Exception e) {
            // Откат транзакции в случае ошибки
            if (transaction.isActive()) {
                transaction.rollback();
            }

            // Установка статуса ошибки и сообщения об ошибке
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error creating new user.");
            e.printStackTrace();
        } finally {
            // Закрытие EntityManager и EntityManagerFactory
            em.close();
            emf.close();
        }
    }
}
