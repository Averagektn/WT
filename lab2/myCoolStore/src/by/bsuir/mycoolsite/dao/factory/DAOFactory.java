package by.bsuir.mycoolsite.dao.factory;

import by.bsuir.mycoolsite.dao.*;
import by.bsuir.mycoolsite.dao.impl.*;

/**
 * Factory class for obtaining Data Access Object (DAO) instances.
 * This class follows the Singleton pattern with a single instance accessible via getInstance().
 */
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
        // Private constructor to enforce Singleton pattern.
    }

    /**
     * Returns the singleton instance of DAOFactory.
     *
     * @return the singleton instance of DAOFactory
     */
    public static DAOFactory getInstance() {
        return instance;
    }

    /**
     * Returns the implementation of CategoryDAO.
     *
     * @return the CategoryDAO implementation
     */
    public CategoryDAO getCategoryDAO() {
        return sqlCategoryImpl;
    }

    /**
     * Returns the implementation of AgeRestrictionDAO.
     *
     * @return the AgeRestrictionDAO implementation
     */
    public AgeRestrictionDAO getAgeRestrictionDAO() {
        return enumAgeRestrictionImpl;
    }

    /**
     * Returns the implementation of FilmDAO.
     *
     * @return the FilmDAO implementation
     */
    public FilmDAO getFilmDAO() {
        return sqlFilmImpl;
    }

    /**
     * Returns the implementation of UserDAO.
     *
     * @return the UserDAO implementation
     */
    public UserDAO getUserDAO() {
        return sqlUserImpl;
    }

    /**
     * Returns the implementation of FeedbackDAO.
     *
     * @return the FeedbackDAO implementation
     */
    public FeedbackDAO getFeedbackDAO() {
        return sqlFeedbackImpl;
    }

    /**
     * Returns the implementation of CartDAO.
     *
     * @return the CartDAO implementation
     */
    public CartDAO getCartDAO() {
        return sqlCartImpl;
    }

    /**
     * Returns the implementation of LibraryDAO.
     *
     * @return the LibraryDAO implementation
     */
    public LibraryDAO getLibraryDAO() {
        return sqlLibraryImpl;
    }
}
