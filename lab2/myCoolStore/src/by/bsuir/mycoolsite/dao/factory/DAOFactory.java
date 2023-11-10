package by.bsuir.mycoolsite.dao.factory;

import by.bsuir.mycoolsite.dao.*;
import by.bsuir.mycoolsite.dao.impl.*;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final FilmDAO sqlFilmImpl = new SQLFilmDAO();
    private final UserDAO sqlUserImpl = new SQLUserDAO();
    private final FeedbackDAO sqlFeedbackImpl = new SQLFeedbackDAO();
    private final CartDAO sqlCartImpl = new SQLCartDAO();
    private final LibraryDAO sqlLibraryImpl = new SQLLibraryDAO();

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public FilmDAO getFilmDAO() {
        return sqlFilmImpl;
    }

    public UserDAO getUserDAO() {
        return sqlUserImpl;
    }

    public FeedbackDAO getFeedbackDAO() {
        return sqlFeedbackImpl;
    }
    public CartDAO getCartDAO(){
        return sqlCartImpl;
    }

    public LibraryDAO getLibraryDAO(){
        return sqlLibraryImpl;
    }
}
