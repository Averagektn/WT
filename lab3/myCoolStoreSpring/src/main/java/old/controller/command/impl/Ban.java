package by.bsuir.mycoolsite.controller.command.impl;

import by.bsuir.mycoolsite.controller.command.Command;
import by.bsuir.mycoolsite.controller.command.exception.CommandException;
import by.bsuir.mycoolsite.controller.page.PageName;
import by.bsuir.mycoolsite.controller.session.SessionAttribute;
import by.bsuir.mycoolsite.service.FeedbackService;
import by.bsuir.mycoolsite.service.UserService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of the {@code Command} interface for banning a user and deleting their feedbacks.
 */
public class Ban implements Command {
    private static final Logger logger = LogManager.getLogger(Ban.class);
    private static final String USER_ID = "authorId";
    private static final String FILM_ID = "filmId";

    /**
     * Executes the command to ban a user and delete their feedbacks.
     *
     * @param request The HTTP servlet request.
     * @return The response URL after executing the command.
     * @throws CommandException If there is an issue executing the command.
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String response;

        HttpSession session = request.getSession(false);

        long userId = Long.parseLong(request.getParameter(USER_ID));
        long filmId = Long.parseLong(request.getParameter(FILM_ID));
        long adminId = (long) session.getAttribute(SessionAttribute.ID);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        FeedbackService feedbackService = serviceFactory.getFeedbackService();

        try {
            userService.ban(userId, adminId);

            feedbackService.deleteUserFeedbacks(userId);

            response = PageName.FILM.getUrlPattern() + "?filmId=" + filmId;
        } catch (ServiceException e) {
            logger.error("Service exception", e);
            throw new CommandException("Service exception: ", e);
        }

        return response;
    }
}
