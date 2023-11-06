package by.bsuir.mycoolsite.controller.command.impl;

import by.bsuir.mycoolsite.controller.command.Command;
import by.bsuir.mycoolsite.service.UserService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;

public class SignIn implements Command {
    @Override
    public String execute(String request) {
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
            response = "Error duiring login procedure";
        }
        return response;
    }
}
