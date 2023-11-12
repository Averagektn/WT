package by.bsuir.mycoolsite.controller.command.impl;

import by.bsuir.mycoolsite.controller.command.Command;
import by.bsuir.mycoolsite.controller.command.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class AddFilm implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return null;
/*        String response;
        Film film;

        long filmId = Long.parseLong(request.getParameter(FILM_ID));
        String text = request.getParameter(FILM_FEEDBACK);
        long author = (long) session.getAttribute(SessionAttribute.ID);
        int rating = Integer.parseInt(request.getParameter(FILM_RATING));

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        FilmService filmService = serviceFactory.getFilmService();

        try {
            filmService.addNewFilm(film);

            response = PageName.FILM.getUrlPattern() + "?filmId=" + filmId;
        } catch (ServiceException e) {
            //LOG
            System.out.println("Service exception: " + e);
            throw new CommandException("Service exception: ", e);
        }

        return response;*/
    }
}
