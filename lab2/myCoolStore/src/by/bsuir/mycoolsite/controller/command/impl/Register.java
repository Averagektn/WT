package by.bsuir.mycoolsite.controller.command.impl;

import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.bean.enums.Role;
import by.bsuir.mycoolsite.controller.command.Command;
import by.bsuir.mycoolsite.controller.command.exception.CommandException;
import by.bsuir.mycoolsite.controller.page.Page;
import by.bsuir.mycoolsite.controller.page.exception.PageException;
import by.bsuir.mycoolsite.controller.page.impl.MainPage;
import by.bsuir.mycoolsite.service.UserService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;

public class Register implements Command {

    private static final String PARAM_EMAIL = "emailRegister";
    private static final String PARAM_PASSWORD = "passwordRegister";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email = null;
        String password = null;
        String response = null;

        email = request.getParameter(PARAM_EMAIL);
        password = request.getParameter(PARAM_PASSWORD);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        User user = new User(email, password, Role.Customer, User.NOT_BANNED);

        Page pageContent = new MainPage();
        String page = null;

        try {
            userService.registration(user);
            response = pageContent.generate(request);
        } catch (ServiceException e) {
            //LOG
            System.out.println("Service exception: " + e);
            throw new CommandException("Service exception: ", e);
        } catch (PageException e){
            //LOG
            System.out.println("Page exception" + e);
            throw new CommandException("Command page exception: ", e);
        }

        return response;
    }
}
