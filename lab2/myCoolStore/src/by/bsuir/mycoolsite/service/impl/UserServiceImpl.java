package by.bsuir.mycoolsite.service.impl;

import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.dao.UserDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import by.bsuir.mycoolsite.service.UserService;
import by.bsuir.mycoolsite.service.exception.ServiceException;

public class UserServiceImpl implements UserService {
    @Override
    public void singIn(String login, String password) throws ServiceException {
        // проверяем параметры
        if (login == null || login.isEmpty()) {
            throw new ServiceException("Incorrect login");
        }
        // реализуем функционал логинации пользователя в системе
        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoObjectFactory.getUserDAO();
            userDAO.signIn(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        //....
    }

    @Override
    public void singOut(String login) throws ServiceException {
    }

    @Override
    public void registration(User user) throws ServiceException {
    }

}
