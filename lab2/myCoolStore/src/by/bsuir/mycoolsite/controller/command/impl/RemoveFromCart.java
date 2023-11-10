package by.bsuir.mycoolsite.controller.command.impl;

import by.bsuir.mycoolsite.controller.command.Command;
import by.bsuir.mycoolsite.controller.command.exception.CommandException;
import by.bsuir.mycoolsite.controller.page.PageName;
import by.bsuir.mycoolsite.controller.session.SessionAttribute;
import by.bsuir.mycoolsite.service.CartService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class RemoveFromCart implements Command {
    private static final String FILM_ID = "filmID";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String response;
        HttpSession session = request.getSession(false);

        long userId = (long) session.getAttribute(SessionAttribute.ID);
        System.out.println(userId);
        long filmId = Long.parseLong(request.getParameter(FILM_ID));
        System.out.println(filmId);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        CartService cartService = serviceFactory.getCartService();

        try{
            cartService.remove(filmId, userId);

            response = PageName.CART.getUrlPattern();
        } catch (ServiceException e) {
            //LOG
            System.out.println("Service exception: " + e);
            throw new CommandException("Service exception: ", e);
        }

        return response;
    }
}
