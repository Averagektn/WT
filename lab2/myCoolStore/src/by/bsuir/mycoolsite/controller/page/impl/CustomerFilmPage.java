package by.bsuir.mycoolsite.controller.page.impl;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.controller.JSPPageName;
import by.bsuir.mycoolsite.controller.page.Page;
import by.bsuir.mycoolsite.controller.page.exception.PageException;
import by.bsuir.mycoolsite.service.FilmService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class CustomerFilmPage implements Page {

    private static final String FILM = "film";
    private static final String IS_FILM_PAID = "paid";
    @Override
    public String generate(HttpServletRequest request) throws PageException {
        String response;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        FilmService filmService = serviceFactory.getFilmService();
        List<Film> films;

        try {
            films = filmService.getFilms();
            //request.setAttribute(FILMS, films);
            response = JSPPageName.PAGE_MAIN;
        } catch (ServiceException e) {
            //LOG
            System.out.println("Page exception: " + e);
            throw new PageException("Service exception: ", e);
        }

        return response;
    }
}
