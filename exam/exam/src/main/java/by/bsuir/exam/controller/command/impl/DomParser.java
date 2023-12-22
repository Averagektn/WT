package by.bsuir.exam.controller.command.impl;

import by.bsuir.exam.controller.JSPPageName;
import by.bsuir.exam.controller.command.Command;
import by.bsuir.exam.controller.command.exception.CommandException;
import by.bsuir.exam.service.exception.ServiceException;
import by.bsuir.exam.service.factory.ServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DomParser implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String response;

        var serviceFactory = ServiceFactory.getInstance();
        var domService = serviceFactory.getDomService();

        try{
            var data = domService.getData();
            request.setAttribute("dataset", data);

            response = JSPPageName.PAGE_TABLE;
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage());
        }

        return response;
    }
}
