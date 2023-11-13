package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    User signIn(String email, String password) throws ServiceException;

    boolean isFilmOwner(long userId, long filmId) throws ServiceException;

    boolean isBanned(long id) throws ServiceException;

    long registration(User user) throws ServiceException;

    void ban(long userId, long adminId) throws ServiceException;

    void unban(long userId) throws ServiceException;

    List<User> getBannedUsers() throws ServiceException;
}
