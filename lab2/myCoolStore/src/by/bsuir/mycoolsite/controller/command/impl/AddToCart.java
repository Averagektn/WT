package by.bsuir.mycoolsite.controller.command.impl;

import by.bsuir.mycoolsite.controller.command.Command;
import by.bsuir.mycoolsite.controller.command.exception.CommandException;
import by.bsuir.mycoolsite.controller.page.PageName;
import by.bsuir.mycoolsite.controller.session.SessionAttribute;
import by.bsuir.mycoolsite.service.CartService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of the {@code Command} interface for adding a film to the user's cart.
 */
public class AddToCart implements Command {
    private static final Logger logger = LogManager.getLogger(AddToCart.class);
    private static final String FILM_ID = "filmID";

    /**
     * Executes the command to add a film to the user's cart.
     *
     * @param request The HTTP servlet request.
     * @return The response URL after executing the command.
     * @throws CommandException If there is an issue executing the command.
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String response;

        HttpSession session = request.getSession(false);

        long filmId = Long.parseLong(request.getParameter(FILM_ID));
        long userId = (long) session.getAttribute(SessionAttribute.ID);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        CartService cartService = serviceFactory.getCartService();

        try {
            cartService.addFilm(filmId, userId);

            response = PageName.MAIN.getUrlPattern();
        } catch (ServiceException e) {
            logger.error("Service exception", e);
            throw new CommandException("Service exception: ", e);
        }

        return response;
    }
}
