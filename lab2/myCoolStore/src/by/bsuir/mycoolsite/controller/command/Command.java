package by.bsuir.mycoolsite.controller.command;

import by.bsuir.mycoolsite.controller.command.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Interface for command execution.
 */
public interface Command {
    /**
     * Executes the command.
     *
     * @param request The HTTP servlet request.
     * @return The response URL after executing the command.
     * @throws CommandException If there is an issue executing the command.
     */
    String execute(HttpServletRequest request) throws CommandException;
}

