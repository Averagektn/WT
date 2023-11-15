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

@MultipartConfig
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.class);

    public Controller() {
        super();
    }

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
            logger.error("RequestDispatcher in NULL");
            errorMessageDirectlyFromResponse(response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
        Command command = CommandProvider.getInstance().getCommand(commandName);
        String page;

        logger.info("Command " + commandName + " received");

        try {
            page = command.execute(request);
            System.out.println(page);
        } catch (CommandException e) {
            //LOG
            System.out.println("Command exception in Controller " + e);
            page = JSPPageName.PAGE_ERROR;
        } catch (Exception e) {
            //LOG
            System.out.println("Exception in Controller " + e);
            page = JSPPageName.PAGE_ERROR;
        }

        try {
            response.sendRedirect(page);
        } catch (IOException e) {
            //LOG
            System.out.println("Send redirect exception: " + e);
            errorMessageDirectlyFromResponse(response);
        }
    }

    private void errorMessageDirectlyFromResponse(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println("E R R O R");
    }
}
