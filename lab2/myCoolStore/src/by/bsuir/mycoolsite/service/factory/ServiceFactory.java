package by.bsuir.mycoolsite.service.factory;

import by.bsuir.mycoolsite.service.*;
import by.bsuir.mycoolsite.service.impl.*;

/**
 * Factory class for obtaining service instances.
 * This class follows the Singleton pattern with a single instance accessible via getInstance().
 */
public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final UserService userService = new UserServiceImpl();
    private final FilmService filmService = new FilmServiceImpl();
    private final FeedbackService feedbackService = new FeedbackServiceImpl();
    private final CartService cartService = new CartServiceImpl();
    private final LibraryService libraryService = new LibraryServiceImpl();
    private final AgeRestrictionService ageRestrictionService = new AgeRestrictionServiceImpl();
    private final CategoryService categoryService = new CategoryServiceImpl();

    private ServiceFactory() {
        // Private constructor to enforce Singleton pattern.
    }

    /**
     * Returns the singleton instance of ServiceFactory.
     *
     * @return the singleton instance of ServiceFactory
     */
    public static ServiceFactory getInstance() {
        return instance;
    }

    /**
     * Returns the implementation of AgeRestrictionService.
     *
     * @return the AgeRestrictionService implementation
     */
    public AgeRestrictionService getAgeRestrictionService() {
        return ageRestrictionService;
    }

    /**
     * Returns the implementation of CategoryService.
     *
     * @return the CategoryService implementation
     */
    public CategoryService getCategoryService() {
        return categoryService;
    }

    /**
     * Returns the implementation of UserService.
     *
     * @return the UserService implementation
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Returns the implementation of FilmService.
     *
     * @return the FilmService implementation
     */
    public FilmService getFilmService() {
        return filmService;
    }

    /**
     * Returns the implementation of FeedbackService.
     *
     * @return the FeedbackService implementation
     */
    public FeedbackService getFeedbackService() {
        return feedbackService;
    }

    /**
     * Returns the implementation of CartService.
     *
     * @return the CartService implementation
     */
    public CartService getCartService() {
        return cartService;
    }

    /**
     * Returns the implementation of LibraryService.
     *
     * @return the LibraryService implementation
     */
    public LibraryService getLibraryService() {
        return libraryService;
    }
}