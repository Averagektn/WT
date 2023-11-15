package by.bsuir.mycoolsite.service.impl;

import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.dao.UserDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import by.bsuir.mycoolsite.service.UserService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public User signIn(String email, String password) throws ServiceException {
        User user;

        if (email == null || email.isEmpty()) {
            logger.error("Incorrect email");
            throw new ServiceException("Incorrect email");
        }

        if (password.length() < 7) {
            logger.error("Incorrect password length");
            throw new ServiceException("Password length is too low");
        }

        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoObjectFactory.getUserDAO();

        try {
            user = userDAO.signIn(email, password);
        } catch (DAOException e) {
            logger.error("DAO Exception", e);
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public boolean isFilmOwner(long userId, long filmId) throws ServiceException {
        boolean isFilmOwner;

        if (userId < 1) {
            logger.error("Incorrect user ID");
            throw new ServiceException("Incorrect user ID");
        }

        if (filmId < 1) {
            logger.error("Incorrect film ID");
            throw new ServiceException("Incorrect film ID");
        }

        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoObjectFactory.getUserDAO();

        try {
            isFilmOwner = userDAO.isFilmOwner(userId, filmId);
        } catch (DAOException e) {
            logger.error("DAO Exception", e);
            throw new ServiceException(e);
        }

        return isFilmOwner;
    }

    @Override
    public boolean isBanned(long id) throws ServiceException {
        boolean isBanned;

        if (id < 1) {
            logger.error("Incorrect user ID");
            throw new ServiceException("Incorrect user ID");
        }

        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoObjectFactory.getUserDAO();

        try {
            isBanned = userDAO.isBanned(id);
        } catch (DAOException e) {
            logger.error("DAO Exception" + e);
            throw new ServiceException(e);
        }

        return isBanned;
    }

    @Override
    public long registration(User user) throws ServiceException {
        long id;

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            logger.error("Incorrect email");
            throw new ServiceException("Incorrect email");
        }

        if (user.getPassword().length() < 7) {
            logger.error("Incorrect password length");
            throw new ServiceException("Password length is too low");
        }

        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoObjectFactory.getUserDAO();
            id = userDAO.registration(user);
        } catch (DAOException e) {
            logger.error("DAO Exception", e);
            throw new ServiceException(e);
        }

        return id;
    }

    @Override
    public void ban(long userId, long adminId) throws ServiceException {
        if (userId < 1) {
            logger.error("Incorrect user ID");
            throw new ServiceException("Incorrect user ID");
        }

        if (adminId < 1) {
            logger.error("Incorrect admin ID");
            throw new ServiceException("Incorrect admin ID");
        }

        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoObjectFactory.getUserDAO();

        try {
            userDAO.ban(userId, adminId);
        } catch (DAOException e) {
            logger.error("DAO Exception" + e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void unban(long userId) throws ServiceException {
        if (userId < 1) {
            logger.error("Incorrect user ID");
            throw new ServiceException("Incorrect user ID");
        }

        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoObjectFactory.getUserDAO();

        try {
            userDAO.unban(userId);
        } catch (DAOException e) {
            logger.error("DAO Exception", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getBannedUsers() throws ServiceException {
        List<User> users;

        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoObjectFactory.getUserDAO();

        try {
            users = userDAO.getBannedUsers();
        } catch (DAOException e) {
            logger.error("DAO Exception" + e);
            throw new ServiceException(e);
        }

        return users;
    }
}
