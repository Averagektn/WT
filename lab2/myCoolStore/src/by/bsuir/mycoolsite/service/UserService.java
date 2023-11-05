package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.service.exception.ServiceException;

public interface UserService {
    void singIn(String email, String password) throws ServiceException;

    void singOut(String email) throws ServiceException;

    void registration(User user) throws ServiceException;
}
