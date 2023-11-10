package by.bsuir.mycoolsite.dao.factory;

import by.bsuir.mycoolsite.dao.CartDAO;
import by.bsuir.mycoolsite.dao.FeedbackDAO;
import by.bsuir.mycoolsite.dao.FilmDAO;
import by.bsuir.mycoolsite.dao.UserDAO;
import by.bsuir.mycoolsite.dao.impl.SQLCartDAO;
import by.bsuir.mycoolsite.dao.impl.SQLFeedbackDAO;
import by.bsuir.mycoolsite.dao.impl.SQLFilmDAO;
import by.bsuir.mycoolsite.dao.impl.SQLUserDAO;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final FilmDAO sqlFilmImpl = new SQLFilmDAO();
    private final UserDAO sqlUserImpl = new SQLUserDAO();
    private final FeedbackDAO sqlFeedbackImpl = new SQLFeedbackDAO();
    private final CartDAO sqlCartImpl = new SQLCartDAO();

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
}
