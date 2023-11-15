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

/**
 * Filter class for restricting access to admin-only pages.
 * Extends HttpFilter and redirects non-admin users to the main page if not authenticated as an admin.
 */
public class AdminFilter extends HttpFilter {

    /**
     * Filters incoming requests, allowing only admin users to proceed.
     * If the user is not an admin, redirects to the main page.
     *
     * @param req   the HttpServletRequest object representing the incoming request
     * @param res   the HttpServletResponse object representing the response
     * @param chain the FilterChain object for invoking the next filter in the chain
     * @throws IOException      if an I/O error occurs during the filter execution
     * @throws ServletException if the filter encounters an exception during processing
     */
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        boolean isAdmin = session.getAttribute(SessionAttribute.IS_ADMIN) != null;

        if (isAdmin) {
            chain.doFilter(req, res);
        } else {
            res.sendRedirect(PageName.MAIN.getUrlPattern());
        }
    }
}
