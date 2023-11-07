package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.service.exception.ServiceException;

public interface UserService {
    User signIn(String email, String password) throws ServiceException;

    long registration(User user) throws ServiceException;
}
