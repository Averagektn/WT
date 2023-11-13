package by.bsuir.mycoolsite.dao;

import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.util.List;

public interface UserDAO {
    User signIn(String email, String password) throws DAOException;
    boolean isFilmOwner(long userId, long filmId) throws DAOException;
    boolean isBanned(long id) throws DAOException;
    long registration(User user) throws DAOException;
    void ban(long userId, long adminId) throws DAOException;
    void unban(long userId) throws DAOException;
    List<User> getBannedUsers() throws DAOException;
}
