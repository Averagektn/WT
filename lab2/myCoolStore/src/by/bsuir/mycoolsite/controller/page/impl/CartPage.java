package by.bsuir.mycoolsite.controller.page.impl;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.controller.JSPPageName;
import by.bsuir.mycoolsite.controller.page.Page;
import by.bsuir.mycoolsite.controller.page.exception.PageException;
import by.bsuir.mycoolsite.controller.session.SessionAttribute;
import by.bsuir.mycoolsite.service.CartService;
import by.bsuir.mycoolsite.service.UserService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import by.bsuir.mycoolsite.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public class CartPage implements Page {
    private static final Logger logger = LogManager.getLogger(CartPage.class);
    private static final String FILMS = "films";
    private static final String TOTAL = "total";

    @Override
    public String generate(HttpServletRequest request) throws PageException {
        String response;

        BigDecimal total = BigDecimal.ZERO;
        List<Film> films;

        HttpSession session = request.getSession(false);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        CartService cartService = serviceFactory.getCartService();
        UserService userService = serviceFactory.getUserService();

        try {
            long userId = (long) session.getAttribute(SessionAttribute.ID);

            if (userService.isBanned(userId)) {
                return JSPPageName.PAGE_BAN;
            }

            films = cartService.getCart(userId);

            for (Film film : films) {
                total = total.add(film.getRealPrice());
            }

            request.setAttribute(FILMS, films);
            request.setAttribute(TOTAL, total);

            response = JSPPageName.PAGE_CART;
        } catch (ServiceException e) {
            logger.error("Service exception: ", e);
            throw new PageException("Service exception: ", e);
        }

        return response;
    }
}
