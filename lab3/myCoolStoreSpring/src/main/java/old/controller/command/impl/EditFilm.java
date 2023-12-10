package by.bsuir.mycoolsite.controller.command.impl;

import by.bsuir.mycoolsite.bean.Category;
import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.bean.Media;
import by.bsuir.mycoolsite.bean.enums.AgeRestriction;
import by.bsuir.mycoolsite.controller.command.Command;
import by.bsuir.mycoolsite.controller.command.exception.CommandException;
import by.bsuir.mycoolsite.controller.page.PageName;
import by.bsuir.mycoolsite.service.FilmService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@code Command} interface for editing film information.
 */
public class EditFilm implements Command {
    private static final Logger logger = LogManager.getLogger(EditFilm.class);
    private static final String FILM_ID = "filmId";
    private static final String FILM_TITLE = "filmTitle";
    private static final String FILM_AUTHOR = "filmAuthor";
    private static final String FILM_CATEGORIES = "filmCategory";
    private static final String FILM_AGE_RESTRICTION = "filmAgeRestriction";
    private static final String FILM_DESCRIPTION = "filmDescription";
    private static final String FILM_PRICE = "filmPrice";
    private static final String FILM_DISCOUNT = "filmDiscount";

    /**
     * Executes the command to edit film information.
     *
     * @param request The HTTP servlet request.
     * @return The response URL after executing the command.
     * @throws CommandException If there is an issue executing the command.
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String response;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        FilmService filmService = serviceFactory.getFilmService();

        try {
            Film film = getFilm(request);

            filmService.editFilm(film);

            response = PageName.MAIN.getUrlPattern();
        } catch (ServiceException e) {
            logger.error("Service exception", e);
            throw new CommandException("Service exception: ", e);
        } catch (ServletException | IOException e) {
            logger.error("Exception in film editor", e);
            throw new CommandException("Exception in film editor: " + e.getMessage(), e);
        }

        return response;
    }

    /**
     * Extracts film information from the request.
     *
     * @param request The HTTP servlet request.
     * @return The film object with extracted information.
     * @throws ServletException If there is an issue with the servlet.
     * @throws IOException      If there is an issue with input or output.
     */
    private Film getFilm(HttpServletRequest request) throws ServletException, IOException {
        long filmId = Long.parseLong(request.getParameter(FILM_ID));
        String title = request.getParameter(FILM_TITLE);
        String author = request.getParameter(FILM_AUTHOR);
        String description = request.getParameter(FILM_DESCRIPTION);
        AgeRestriction ageRestriction = AgeRestriction.getAgeRestrictionFromString(request.getParameter(FILM_AGE_RESTRICTION));
        BigDecimal price = new BigDecimal(request.getParameter(FILM_PRICE));
        int discount = Integer.parseInt(request.getParameter(FILM_DISCOUNT));
        List<Category> categories = new ArrayList<>();
        for (String cat : request.getParameterValues(FILM_CATEGORIES)) {
            categories.add(new Category(Long.parseLong(cat)));
        }

        return new Film(filmId, description, price, new Media(0), discount, author, ageRestriction, title, categories);
    }
}

