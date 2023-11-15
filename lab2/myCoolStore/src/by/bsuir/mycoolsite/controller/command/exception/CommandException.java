package by.bsuir.mycoolsite.controller.command.exception;

import by.bsuir.mycoolsite.exception.ProjectException;

import java.io.Serial;

public class CommandException extends ProjectException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CommandException(String msg) {
        super(msg);
    }

    public CommandException(String msg, Exception e) {
        super(msg, e);
    }
}
