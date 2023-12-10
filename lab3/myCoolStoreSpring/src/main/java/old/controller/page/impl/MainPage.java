package by.bsuir.mycoolsite.controller.page.impl;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.controller.JSPPageName;
import by.bsuir.mycoolsite.controller.page.Page;
import by.bsuir.mycoolsite.controller.page.exception.PageException;
import by.bsuir.mycoolsite.service.FilmService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Implementation of the Page interface for handling the main page.
 */
public class MainPage implements Page {
    private static final Logger logger = LogManager.getLogger(MainPage.class);
    private static final String FILMS = "films";

    /**
     * {@inheritDoc}
     */
    @Override
    public String generate(HttpServletRequest request) throws PageException {
        String response;

        List<Film> films;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        FilmService filmService = serviceFactory.getFilmService();

        try {
            films = filmService.getFilms();

            request.setAttribute(FILMS, films);

            response = JSPPageName.PAGE_MAIN;
        } catch (ServiceException e) {
            logger.error("Service exception: ", e);
            throw new PageException("Service exception: ", e);
        }

        return response;
    }
}
