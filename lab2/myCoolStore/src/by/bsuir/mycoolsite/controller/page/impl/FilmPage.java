package by.bsuir.mycoolsite.controller.page.impl;

import by.bsuir.mycoolsite.bean.Feedback;
import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.controller.JSPPageName;
import by.bsuir.mycoolsite.controller.page.Page;
import by.bsuir.mycoolsite.controller.page.exception.PageException;
import by.bsuir.mycoolsite.controller.session.SessionAttribute;
import by.bsuir.mycoolsite.service.FilmService;
import by.bsuir.mycoolsite.service.UserService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class FilmPage implements Page {

    private static final String FEEDBACKS = "feedbacks";
    private static final String FILM = "film";
    private static final String IS_FILM_PAID = "isPaid";
    private static final String IS_BANNED = "isBanned";
    private static final String FILM_ID = "filmId";
    @Override
    public String generate(HttpServletRequest request) throws PageException {
        String response;
        boolean isFilmOwner, isBanned;
        Film film;
        List<Feedback> feedbacks;

        HttpSession session = request.getSession(false);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        FilmService filmService = serviceFactory.getFilmService();
        UserService userService = serviceFactory.getUserService();

        try {
            long filmId = Long.parseLong(request.getParameter(FILM_ID));

            feedbacks = filmService.getFilmFeedbacks(filmId);
            film = filmService.getFilmById(filmId);
            request.setAttribute(FILM, film);
            request.setAttribute(FEEDBACKS, feedbacks);
            System.out.println(film.getDiscount());

            if (session != null && session.getAttribute(SessionAttribute.ID) != null){
                long userId = (long)session.getAttribute(SessionAttribute.ID);

                isFilmOwner = userService.isFilmOwner(userId, filmId);
                isBanned = userService.isBanned(userId);
                request.setAttribute(IS_BANNED, isBanned);
                request.setAttribute(IS_FILM_PAID, isFilmOwner);
            } else {
                request.setAttribute(IS_BANNED, Boolean.TRUE);
                request.setAttribute(IS_FILM_PAID, Boolean.FALSE);
            }

            response = JSPPageName.PAGE_FILM;
        } catch (ServiceException e) {
            //LOG
            System.out.println("Page exception: " + e);
            throw new PageException("Service exception: ", e);
        }

        return response;
    }
}
