package by.bsuir.mycoolsite.filter;

import by.bsuir.mycoolsite.controller.page.PageName;
import by.bsuir.mycoolsite.controller.session.SessionAttribute;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class CustomerFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpSession session = req.getSession(false);

        Object id = session.getAttribute(SessionAttribute.ID);
        Object isAdmin = session.getAttribute(SessionAttribute.IS_ADMIN);

        if (id == null || isAdmin != null){
            res.sendRedirect(PageName.MAIN.getUrlPattern());
        } else {
            chain.doFilter(req, res);
        }
    }
}
