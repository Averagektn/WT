package by.bsuir.mycoolsite.controller.command.impl;

import by.bsuir.mycoolsite.controller.command.Command;
import by.bsuir.mycoolsite.controller.command.exception.CommandException;
import by.bsuir.mycoolsite.controller.page.PageName;
import by.bsuir.mycoolsite.controller.session.SessionAttribute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Implementation of the {@code Command} interface for user sign-out.
 */
public class SignOut implements Command {

    /**
     * Executes the command for user sign-out.
     *
     * @param request The HTTP servlet request.
     * @return The response URL after executing the command.
     * @throws CommandException If there is an issue executing the command.
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String response = PageName.MAIN.getUrlPattern();

        HttpSession session = request.getSession();

        session.removeAttribute(SessionAttribute.ID);
        session.removeAttribute(SessionAttribute.IS_ADMIN);

        return response;
    }
}
