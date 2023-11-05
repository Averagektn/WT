package by.bsuir.mycoolsite.service.factory;

import by.bsuir.mycoolsite.service.FilmService;
import by.bsuir.mycoolsite.service.UserService;
import by.bsuir.mycoolsite.service.impl.FilmServiceImpl;
import by.bsuir.mycoolsite.service.impl.UserServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final UserService clientService = new UserServiceImpl();
    private final FilmService libraryService = new FilmServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return clientService;
    }

    public FilmService getFilmService() {
        return libraryService;
    }
}