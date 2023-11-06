package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.service.exception.ServiceException;

public interface UserService {
    void signIn(String email, String password) throws ServiceException;

    void signOut(String email) throws ServiceException;

    void registration(User user) throws ServiceException;
}
