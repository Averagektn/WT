package by.bsuir.exam.controller.command;

import by.bsuir.exam.controller.command.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {
    String execute(HttpServletRequest request) throws CommandException;
}