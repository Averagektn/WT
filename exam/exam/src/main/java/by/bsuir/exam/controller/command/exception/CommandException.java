package by.bsuir.exam.controller.command.exception;

import by.bsuir.exam.exception.ProjectException;

import java.io.Serial;

public class CommandException extends ProjectException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new CommandException with the specified detail message.
     *
     * @param msg The detail message.
     */
    public CommandException(String msg) {
        super(msg);
    }

    /**
     * Constructs a new CommandException with the specified detail message and cause.
     *
     * @param msg The detail message.
     * @param e   The cause.
     */
    public CommandException(String msg, Exception e) {
        super(msg, e);
    }
}
