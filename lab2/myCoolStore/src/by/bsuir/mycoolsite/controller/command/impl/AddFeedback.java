package by.bsuir.mycoolsite.controller.command.impl;

import by.bsuir.mycoolsite.bean.Feedback;
import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.controller.command.Command;
import by.bsuir.mycoolsite.controller.command.exception.CommandException;
import by.bsuir.mycoolsite.controller.page.PageName;
import by.bsuir.mycoolsite.controller.session.SessionAttribute;
import by.bsuir.mycoolsite.service.FeedbackService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AddFeedback implements Command {
    private static final String FILM_ID = "filmID";
    private static final String FILM_FEEDBACK = "filmFeedback";
    private static final String FILM_RATING = "rating";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String response;
        HttpSession session = request.getSession(false);

        long filmId = Long.parseLong(request.getParameter(FILM_ID));
        String text = request.getParameter(FILM_FEEDBACK);
        long author = (long) session.getAttribute(SessionAttribute.ID);
        int rating = Integer.parseInt(request.getParameter(FILM_RATING));

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        FeedbackService feedbackService = serviceFactory.getFeedbackService();
        Feedback feedback = new Feedback(new User(author), new Film(filmId), text, rating);

        try {
            feedbackService.addFeedback(feedback);

            response = PageName.FILM.getUrlPattern() + "?filmId=" + filmId;
        } catch (ServiceException e) {
            //LOG
            System.out.println("Service exception: " + e);
            throw new CommandException("Service exception: ", e);
        }

        return response;
    }
}
