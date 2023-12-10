package by.bsuir.mycoolsite.controller.page.exception;

import by.bsuir.mycoolsite.exception.ProjectException;

import java.io.Serial;

/**
 * Custom exception class for page-related exceptions.
 */
public class PageException extends ProjectException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a PageException with the specified detail message.
     *
     * @param msg The detail message.
     */
    public PageException(String msg) {
        super(msg);
    }

    /**
     * Constructs a PageException with the specified detail message and cause.
     *
     * @param msg The detail message.
     * @param e   The cause.
     */
    public PageException(String msg, Exception e) {
        super(msg, e);
    }
}
