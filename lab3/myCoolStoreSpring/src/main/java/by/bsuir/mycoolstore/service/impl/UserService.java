package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.dao.UserRepository;
import by.bsuir.mycoolstore.entity.UserEntity;
import by.bsuir.mycoolstore.service.exception.ServiceException;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long registration(UserEntity user) throws ServiceException {
        var savedUser = userRepository.save(user);

        logger.info("Registered user: " + savedUser.getUsrId() + " " + savedUser.getUsrEmail());

        return savedUser.getUsrId();
    }

    public UserEntity signIn(UserEntity user) throws ServiceException {
        return userRepository.findByUsrEmailAndUsrPassword(user.getUsrEmail(), user.getUsrPassword());

/*        if (email == null || email.isEmpty()) {
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

        return user;*/
    }

/*    @Override


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
    }*/
}
