package by.bsuir.mycoolsite.controller.page.impl;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.controller.JSPPageName;
import by.bsuir.mycoolsite.controller.page.Page;
import by.bsuir.mycoolsite.controller.page.exception.PageException;
import by.bsuir.mycoolsite.service.FilmService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

public class MainPage implements Page {
    @Override
    public String generate(HttpServletRequest request) throws PageException {
        String response = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        FilmService filmService = serviceFactory.getFilmService();
        List<Film> films = new ArrayList<>();

        try {
            films = filmService.getFilms();
            response = JSPPageName.PAGE_MAIN;

            //List<String> filmNames = films.stream().map(Film::getAuthor).toList();
            //request.setAttribute();

        } catch (ServiceException e) {
            //LOG
            System.out.println("Page exception: " + e.toString());
            throw new PageException("Service exception: ", e);
        }

        return response;
    }
}
