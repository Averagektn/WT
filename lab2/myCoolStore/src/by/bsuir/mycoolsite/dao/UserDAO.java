package by.bsuir.mycoolsite.dao;

import by.bsuir.mycoolsite.bean.User;

public interface UserDAO {
    void signIn(String email, String password);

    void registration(User user);
}
