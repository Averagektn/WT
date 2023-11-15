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
    private final AgeRestrictionDAO enumAgeRestrictionImpl = new EnumAgeRestrictionDAO();
    private final CategoryDAO sqlCategoryImpl = new SQLCategoryDAO();

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public CategoryDAO getCategoryDAO() {
        return sqlCategoryImpl;
    }

    public AgeRestrictionDAO getAgeRestrictionDAO() {
        return enumAgeRestrictionImpl;
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

    public CartDAO getCartDAO() {
        return sqlCartImpl;
    }

    public LibraryDAO getLibraryDAO() {
        return sqlLibraryImpl;
    }
}
