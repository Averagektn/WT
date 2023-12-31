package by.bsuir.exam.controller.command;

import by.bsuir.exam.controller.command.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request) throws CommandException;
}