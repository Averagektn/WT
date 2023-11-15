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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LibraryPage implements Page {
    private static final Logger logger = LogManager.getLogger(LibraryPage.class);
    private static final String FILMS = "films";

    @Override
    public String generate(HttpServletRequest request) throws PageException {
        String response;

        HttpSession session = request.getSession(false);

        List<Film> films;

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
            logger.error("Service exception: ", e);
            throw new PageException("Service exception: ", e);
        }

        return response;
    }
}
