package by.bsuir.exam.controller;

import by.bsuir.exam.controller.command.Command;
import by.bsuir.exam.controller.command.CommandProvider;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Controller extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
        Command command = CommandProvider.getInstance().getCommand(commandName);
        String page;

        try {
            page = command.execute(request);
        } catch (Exception e) {
            page = JSPPageName.PAGE_ERROR;
        }

        try {
            response.sendRedirect(page);
        } catch (IOException e) {
            errorMessageDirectlyFromResponse(response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    private void errorMessageDirectlyFromResponse(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println("E R R O R");
    }
}
