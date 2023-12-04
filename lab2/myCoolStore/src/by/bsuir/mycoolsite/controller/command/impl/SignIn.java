package by.bsuir.mycoolsite.controller.command.impl;

import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.broker.RabbitMQ;
import by.bsuir.mycoolsite.controller.command.Command;
import by.bsuir.mycoolsite.controller.command.exception.CommandException;
import by.bsuir.mycoolsite.controller.page.PageName;
import by.bsuir.mycoolsite.controller.session.SessionAttribute;
import by.bsuir.mycoolsite.service.UserService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of the {@code Command} interface for user sign-in.
 */
public class SignIn implements Command {
    private static final Logger logger = LogManager.getLogger(SignIn.class);
    private static final String PARAM_EMAIL = "emailAuthorization";
    private static final String PARAM_PASSWORD = "passwordAuthorization";

    /**
     * Executes the command for user sign-in.
     *
     * @param request The HTTP servlet request.
     * @return The response URL after executing the command.
     * @throws CommandException If there is an issue executing the command.
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String response;

        HttpSession session = request.getSession(true);

        String email = request.getParameter(PARAM_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);

        RabbitMQ.sendMessage("Authorisation of " + email);
        //logger.info("Authorisation of " + email);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService clientService = serviceFactory.getUserService();

        try {
            User user = clientService.signIn(email, password);

            session.setAttribute(SessionAttribute.ID, user.getId());
            if (user.isAdmin()) {
                session.setAttribute(SessionAttribute.IS_ADMIN, true);
            }

            response = PageName.MAIN.getUrlPattern();
        } catch (ServiceException e) {
            logger.error("Service exception: ", e);
            throw new CommandException("Service exception", e);
        }

        return response;
    }
}

