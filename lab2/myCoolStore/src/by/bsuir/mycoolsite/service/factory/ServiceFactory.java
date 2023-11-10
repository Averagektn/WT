package by.bsuir.mycoolsite.service.factory;

import by.bsuir.mycoolsite.service.*;
import by.bsuir.mycoolsite.service.impl.*;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final UserService userService = new UserServiceImpl();
    private final FilmService filmService = new FilmServiceImpl();
    private final FeedbackService feedbackService = new FeedbackServiceImpl();
    private final CartService cartService = new CartServiceImpl();
    private final LibraryService libraryService = new LibraryServiceImpl();
    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public FilmService getFilmService() {
        return filmService;
    }
    public FeedbackService getFeedbackService() { return feedbackService; }
    public CartService getCartService() { return cartService; }
    public LibraryService getLibraryService() { return libraryService; }
}