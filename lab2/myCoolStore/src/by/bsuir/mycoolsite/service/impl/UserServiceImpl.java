package by.bsuir.mycoolsite.service.impl;

import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.dao.UserDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import by.bsuir.mycoolsite.service.UserService;
import by.bsuir.mycoolsite.service.exception.ServiceException;

public class UserServiceImpl implements UserService {
    @Override
    public User signIn(String email, String password) throws ServiceException {
        User user;

        if (email == null || email.isEmpty()) {
            //LOG
            System.out.println("ServiceException, incorrect email");
            throw new ServiceException("Incorrect email");
        }

        if (password.length() < 7) {
            //LOG
            System.out.println("Service exception, incorrect password length");
            throw new ServiceException("Password length is too low");
        }

        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoObjectFactory.getUserDAO();
            user = userDAO.signIn(email, password);
        } catch (DAOException e) {
            //LOG
            System.out.println("DAOException in UserServiceImpl " + e);
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public long registration(User user) throws ServiceException {
        long id;

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            //LOG
            System.out.println("ServiceException, incorrect email");
            throw new ServiceException("Incorrect email");
        }

        if (user.getPassword().length() < 7) {
            //LOG
            System.out.println("Service exception, incorrect password length");
            throw new ServiceException("Password length is too low");
        }

        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoObjectFactory.getUserDAO();
            id = userDAO.registration(user);
        } catch (DAOException e) {
            //LOG
            System.out.println("DAOException in UserServiceImpl " + e);
            throw new ServiceException(e);
        }

        return id;
    }
}
