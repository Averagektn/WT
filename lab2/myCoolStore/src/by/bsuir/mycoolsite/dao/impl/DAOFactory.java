package by.bsuir.mycoolsite.dao.impl;

import by.bsuir.mycoolsite.dao.FilmDAO;
import by.bsuir.mycoolsite.dao.UserDAO;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final FilmDAO sqlBookImpl = new SQLFilmDAO();
    private final UserDAO sqlUserImpl = new SQLUserDAO();
    private DAOFactory(){

    }
    public static DAOFactory getInstance(){
        return instance;
    }
    public FilmDAO getFilmDAO(){
        return sqlBookImpl;
    }
    public UserDAO getUserDAO(){
        return sqlUserImpl;
    }
}
