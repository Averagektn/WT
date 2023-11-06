package by.bsuir.mycoolsite.service.factory;

import by.bsuir.mycoolsite.service.FilmService;
import by.bsuir.mycoolsite.service.UserService;
import by.bsuir.mycoolsite.service.impl.FilmServiceImpl;
import by.bsuir.mycoolsite.service.impl.UserServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final UserService userService = new UserServiceImpl();
    private final FilmService filmService = new FilmServiceImpl();

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
}