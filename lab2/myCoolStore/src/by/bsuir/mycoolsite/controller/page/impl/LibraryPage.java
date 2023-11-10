package by.bsuir.mycoolsite.controller.page.impl;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.controller.JSPPageName;
import by.bsuir.mycoolsite.controller.page.Page;
import by.bsuir.mycoolsite.controller.page.exception.PageException;
import by.bsuir.mycoolsite.controller.session.SessionAttribute;
import by.bsuir.mycoolsite.service.LibraryService;
import by.bsuir.mycoolsite.service.UserService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class LibraryPage implements Page {
    private static final String FILMS = "films";

    @Override
    public String generate(HttpServletRequest request) throws PageException {
        String response;

        List<Film> films;

        HttpSession session = request.getSession(false);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibraryService libraryService = serviceFactory.getLibraryService();
        UserService userService = serviceFactory.getUserService();

        try {
            long userId = (long) session.getAttribute(SessionAttribute.ID);

            if (userService.isBanned(userId)) {
                return JSPPageName.PAGE_BAN;
            }

            films = libraryService.getUserFilms(userId);

            request.setAttribute(FILMS, films);

            response = JSPPageName.PAGE_LIBRARY;

        } catch (ServiceException e) {
            //LOG
            System.out.println("Page exception: " + e);
            throw new PageException("Service exception: ", e);
        }

        return response;
    }
}
