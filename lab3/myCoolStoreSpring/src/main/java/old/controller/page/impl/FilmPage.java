package by.bsuir.mycoolsite.controller.page.impl;

import by.bsuir.mycoolsite.bean.Feedback;
import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.controller.JSPPageName;
import by.bsuir.mycoolsite.controller.page.Page;
import by.bsuir.mycoolsite.controller.page.exception.PageException;
import by.bsuir.mycoolsite.controller.session.SessionAttribute;
import by.bsuir.mycoolsite.service.CartService;
import by.bsuir.mycoolsite.service.FeedbackService;
import by.bsuir.mycoolsite.service.FilmService;
import by.bsuir.mycoolsite.service.UserService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Implementation of the Page interface for handling the film page.
 */
public class FilmPage implements Page {
    private static final Logger logger = LogManager.getLogger(FilmPage.class);
    private static final String FEEDBACKS = "feedbacks";
    private static final String FILM = "film";
    private static final String IS_FILM_PAID = "isPaid";
    private static final String IS_FILM_IN_CART = "isFilmInCart";
    private static final String IS_BANNED = "isBanned";
    private static final String FILM_ID = "filmId";

    /**
     * {@inheritDoc}
     */
    @Override
    public String generate(HttpServletRequest request) throws PageException {
        String response;

        HttpSession session = request.getSession(false);

        boolean isFilmOwner, isBanned, isFilmInCart;
        Film film;
        List<Feedback> feedbacks;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        FilmService filmService = serviceFactory.getFilmService();
        FeedbackService feedbackService = serviceFactory.getFeedbackService();
        UserService userService = serviceFactory.getUserService();
        CartService cartService = serviceFactory.getCartService();

        try {
            long filmId = Long.parseLong(request.getParameter(FILM_ID));
            feedbacks = feedbackService.getFilmFeedbacks(filmId);
            film = filmService.getFilmById(filmId);
            request.setAttribute(FILM, film);
            request.setAttribute(FEEDBACKS, feedbacks);

            if (session != null && session.getAttribute(SessionAttribute.ID) != null) {
                long userId = (long) session.getAttribute(SessionAttribute.ID);

                isFilmOwner = userService.isFilmOwner(userId, filmId);
                isBanned = userService.isBanned(userId);
                isFilmInCart = cartService.contains(userId, filmId);

                request.setAttribute(IS_FILM_IN_CART, isFilmInCart);
                request.setAttribute(IS_BANNED, isBanned);
                request.setAttribute(IS_FILM_PAID, isFilmOwner);
            } else {
                request.setAttribute(IS_FILM_IN_CART, Boolean.FALSE);
                request.setAttribute(IS_BANNED, Boolean.TRUE);
                request.setAttribute(IS_FILM_PAID, Boolean.FALSE);
            }

            response = JSPPageName.PAGE_FILM;
        } catch (ServiceException e) {
            logger.error("Service exception: ", e);
            throw new PageException("Service exception: ", e);
        }

        return response;
    }
}
