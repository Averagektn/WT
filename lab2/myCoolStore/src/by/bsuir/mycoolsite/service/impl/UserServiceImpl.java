package by.bsuir.mycoolsite.service.impl;

import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.dao.UserDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import by.bsuir.mycoolsite.service.UserService;
import by.bsuir.mycoolsite.service.exception.ServiceException;

public class UserServiceImpl implements UserService {
    @Override
    public void singIn(String email, String password) throws ServiceException {
/*        // проверяем параметры
        if (email == null || email.isEmpty()) {
            throw new ServiceException("Incorrect login");
        }
        // реализуем функционал логинации пользователя в системе
        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoObjectFactory.getUserDAO();
            userDAO.signIn(email, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        //....*/
    }

    @Override
    public void signOut(String email) throws ServiceException {
    }

    @Override
    public void registration(User user) throws ServiceException {
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
            userDAO.registration(user);
        } catch (DAOException e) {
            //LOG
            System.out.println("DAOException in UserServiceImpl");
            throw new ServiceException(e);
        }
    }
}
