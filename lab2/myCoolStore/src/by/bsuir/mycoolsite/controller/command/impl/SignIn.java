package by.bsuir.mycoolsite.controller.command.impl;

import by.bsuir.mycoolsite.controller.JSPPageName;
import by.bsuir.mycoolsite.controller.command.Command;
import by.bsuir.mycoolsite.controller.command.exception.CommandException;
import by.bsuir.mycoolsite.controller.page.PageName;
import by.bsuir.mycoolsite.service.UserService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;

import jakarta.servlet.http.HttpServletRequest;

public class SignIn implements Command {

    private static final String PARAM_EMAIL = "emailAuthorization";
    private static final String PARAM_PASSWORD = "passwordAuthorization";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email;
        String password;
        String response;

        email = request.getParameter(PARAM_EMAIL);
        password = request.getParameter(PARAM_PASSWORD);

        //LOG
        System.out.println("Authorization of " + email);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService clientService = serviceFactory.getUserService();

        try {
            clientService.signIn(email, password);
            response = PageName.MAIN.getUrlPattern();;
        } catch (ServiceException e) {
            //LOG
            System.out.println("Authorization error " + e);
            response = JSPPageName.PAGE_ERROR;
        }

        return response;
    }
}
