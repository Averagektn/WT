package by.bsuir.mycoolsite.controller.command.impl;

import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.bean.enums.Role;
import by.bsuir.mycoolsite.controller.command.Command;
import by.bsuir.mycoolsite.controller.command.exception.CommandException;
import by.bsuir.mycoolsite.controller.page.PageName;
import by.bsuir.mycoolsite.controller.session.SessionAttribute;
import by.bsuir.mycoolsite.service.UserService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class Register implements Command {

    private static final String PARAM_EMAIL = "emailRegister";
    private static final String PARAM_PASSWORD = "passwordRegister";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email;
        String password;
        String response;

        email = request.getParameter(PARAM_EMAIL);
        password = request.getParameter(PARAM_PASSWORD);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        User user = new User(email, password, Role.CUSTOMER, User.NOT_BANNED);

        try {
            long id = userService.registration(user);

            HttpSession session = request.getSession(true);
            session.setAttribute(SessionAttribute.ID, id);

            response = PageName.MAIN.getUrlPattern();
        } catch (ServiceException e) {
            //LOG
            System.out.println("Service exception: " + e);
            throw new CommandException("Service exception: ", e);
        }

        return response;
    }
}
