package by.bsuir.mycoolsite.dao;

import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.dao.exception.DAOException;

public interface UserDAO {
    User signIn(String email, String password) throws DAOException;

    long registration(User user) throws DAOException;
}
