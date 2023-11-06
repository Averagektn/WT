package by.bsuir.mycoolsite.controller.command.impl;

import by.bsuir.mycoolsite.controller.command.Command;
import by.bsuir.mycoolsite.controller.command.exception.CommandException;
import by.bsuir.mycoolsite.service.UserService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;

public class SignIn implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email = null;
        String password = null;
        String response = null;

        // get parameters from request and initialize the variables login and password

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService clientService = serviceFactory.getUserService();

        try {
            clientService.singIn(email, password);
            response = "Welcome";
        } catch (ServiceException e) {
            // write log
            response = "Error during login procedure";
        }
        return response;
    }
}
