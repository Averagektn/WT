package by.bsuir.mycoolsite.controller;

import by.bsuir.mycoolsite.controller.command.Command;
import by.bsuir.mycoolsite.controller.command.CommandProvider;
import by.bsuir.mycoolsite.controller.command.exception.CommandException;
import by.bsuir.mycoolsite.controller.page.Page;
import by.bsuir.mycoolsite.controller.page.PageProvider;
import by.bsuir.mycoolsite.controller.page.exception.PageException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Controller class that handles HTTP requests and responses.
 */
@MultipartConfig
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.class);

    public Controller() {
        super();
    }

    /**
     * Handles HTTP GET requests.
     *
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        Page pageContent = PageProvider.getInstance().getPage(requestURI);
        String page;

        logger.info("URI " + requestURI + " received");

        try {
            page = pageContent.generate(request);
        } catch (PageException e) {
            logger.error("Page exception in Controller", e);
            page = JSPPageName.PAGE_ERROR;
        } catch (Exception e) {
            logger.error("Exception in Controller", e);
            page = JSPPageName.PAGE_ERROR;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        } else {
            logger.error("RequestDispatcher is NULL");
            errorMessageDirectlyFromResponse(response);
        }
    }

    /**
     * Handles HTTP POST requests.
     *
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws IOException If an I/O error occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
        Command command = CommandProvider.getInstance().getCommand(commandName);
        String page;

        logger.info("Command " + commandName + " received");

        try {
            page = command.execute(request);
            System.out.println(page);
        } catch (CommandException e) {
            logger.error("Command exception in Controller ", e);
            page = JSPPageName.PAGE_ERROR;
        } catch (Exception e) {
            logger.error("Exception in Controller ", e);
            page = JSPPageName.PAGE_ERROR;
        }

        try {
            response.sendRedirect(page);
        } catch (IOException e) {
            logger.error("Send redirect exception in Controller", e);
            errorMessageDirectlyFromResponse(response);
        }
    }

    /**
     * Outputs an error message directly to the HttpServletResponse object.
     *
     * @param response The HttpServletResponse object.
     * @throws IOException If an I/O error occurs.
     */
    private void errorMessageDirectlyFromResponse(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println("E R R O R");
    }
}
