package by.bsuir.mycoolstore.controller;

import by.bsuir.mycoolstore.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "Servlet", value = "/Servlet")
public class MyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myCoolStoreSpring");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        UserEntity userEntity = new UserEntity();
        userEntity.setUsrBannedBy(null);
        userEntity.setUsrId(null);
        userEntity.setUsrPassword("Aboba");
        userEntity.setUsrRole("admin");
        userEntity.setUsrEmail("aboba@mail");
        entityManager.persist(userEntity);
        transaction.commit();
    }
}
