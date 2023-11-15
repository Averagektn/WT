package by.bsuir.mycoolsite.controller.command.impl;

import by.bsuir.mycoolsite.controller.command.Command;
import by.bsuir.mycoolsite.controller.command.exception.CommandException;
import by.bsuir.mycoolsite.controller.page.PageName;
import by.bsuir.mycoolsite.service.UserService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Unban implements Command {
    private static final Logger logger = LogManager.getLogger(Unban.class);
    private static final String USER_ID = "userId";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String response;

        long userId = Long.parseLong(request.getParameter(USER_ID));

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        try {
            userService.unban(userId);

            response = PageName.BAN_LIST.getUrlPattern();
        } catch (ServiceException e) {
            logger.error("Service exception: ", e);
            throw new CommandException("Service exception: ", e);
        }

        return response;
    }
}
