package by.bsuir.mycoolsite.controller;

import by.bsuir.mycoolsite.controller.command.Command;
import by.bsuir.mycoolsite.controller.command.CommandProvider;
import by.bsuir.mycoolsite.controller.command.exception.CommandException;
import by.bsuir.mycoolsite.controller.page.Page;
import by.bsuir.mycoolsite.controller.page.PageProvider;
import by.bsuir.mycoolsite.controller.page.exception.PageException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Controller extends HttpServlet {
    private final CommandProvider provider = new CommandProvider();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        Page pageContent = PageProvider.getInstance().getPage(requestURI);
        String page = null;

        // LOG
        System.out.println("URI " + requestURI + " received");

        try {
            page = pageContent.generate(request);
        } catch (PageException e) {
            //LOG
            System.out.println("Command exception in Controller " + e.toString());
            page = JSPPageName.PAGE_ERROR;
        } catch (Exception e) {
            //LOG
            System.out.println("Exception in Controller " + e.toString());
            page = JSPPageName.PAGE_ERROR;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);

        if (dispatcher != null) {
            dispatcher.forward(request, response);
        } else {
            //LOG
            System.out.println("RequestDispatcher is NULL");
            errorMessageDirectlyFromResponse(response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
        Command command = CommandProvider.getInstance().getCommand(commandName);
        String page = null;

        // LOG
        System.out.println("Command " + commandName + " received");

        try {
            page = command.execute(request);
        } catch (CommandException e) {
            //LOG
            System.out.println("Command exception in Controller " + e.toString());
            page = JSPPageName.PAGE_ERROR;
        } catch (Exception e) {
            //LOG
            System.out.println("Exception in Controller " + e.toString());
            page = JSPPageName.PAGE_ERROR;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);

        if (dispatcher != null) {
            dispatcher.forward(request, response);
        } else {
            //LOG
            System.out.println("RequestDispatcher is NULL");
            errorMessageDirectlyFromResponse(response);
        }
    }

    private void errorMessageDirectlyFromResponse(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println("E R R O R");
    }
}
