package by.bsuir.mycoolsite.controller.command;

import by.bsuir.mycoolsite.controller.command.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    public String execute(HttpServletRequest request) throws CommandException;
}
