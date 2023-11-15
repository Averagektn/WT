package by.bsuir.mycoolsite.controller.command.impl;

import by.bsuir.mycoolsite.controller.command.Command;
import by.bsuir.mycoolsite.controller.command.exception.CommandException;
import by.bsuir.mycoolsite.controller.page.PageName;
import by.bsuir.mycoolsite.controller.session.SessionAttribute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SignOut implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String response = PageName.MAIN.getUrlPattern();

        HttpSession session = request.getSession();

        session.removeAttribute(SessionAttribute.ID);
        session.removeAttribute(SessionAttribute.IS_ADMIN);

        return response;
    }
}
