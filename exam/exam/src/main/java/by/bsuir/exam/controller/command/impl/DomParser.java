package by.bsuir.exam.controller.command.impl;

import by.bsuir.exam.controller.command.Command;
import by.bsuir.exam.controller.command.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class DomParser implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
