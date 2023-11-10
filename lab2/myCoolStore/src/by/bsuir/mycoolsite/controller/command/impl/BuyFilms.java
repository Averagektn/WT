package by.bsuir.mycoolsite.controller.command.impl;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.controller.command.Command;
import by.bsuir.mycoolsite.controller.command.exception.CommandException;
import by.bsuir.mycoolsite.controller.page.PageName;
import by.bsuir.mycoolsite.controller.session.SessionAttribute;
import by.bsuir.mycoolsite.service.CartService;
import by.bsuir.mycoolsite.service.LibraryService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class BuyFilms implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String response;

        HttpSession session = request.getSession(false);

        long userId = (long) session.getAttribute(SessionAttribute.ID);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        CartService cartService = serviceFactory.getCartService();
        LibraryService libraryService = serviceFactory.getLibraryService();

        try {
            List<Film> films = cartService.getCart(userId);
            cartService.clear(userId);

            for (Film film: films){
                libraryService.addFilm(userId, film.getId());
            }

            response = PageName.MAIN.getUrlPattern();
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        return response;
    }
}
