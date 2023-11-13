package by.bsuir.mycoolsite.controller.page.impl;

import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.controller.JSPPageName;
import by.bsuir.mycoolsite.controller.page.Page;
import by.bsuir.mycoolsite.controller.page.exception.PageException;
import by.bsuir.mycoolsite.service.UserService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class BanListPage implements Page {
    private static final String BANNED_USERS = "users";
    @Override
    public String generate(HttpServletRequest request) throws PageException {
        String response;

        List<User> users;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        try {
            users = userService.getBannedUsers();

            request.setAttribute(BANNED_USERS, users);

            response = JSPPageName.PAGE_BAN_LIST;
        } catch (ServiceException e) {
            //LOG
            System.out.println("Page exception: " + e);
            throw new PageException("Service exception: ", e);
        }

        return response;
    }
}
