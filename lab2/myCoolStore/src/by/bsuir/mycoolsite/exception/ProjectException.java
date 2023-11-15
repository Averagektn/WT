package by.bsuir.mycoolsite.exception;

import java.io.Serial;

/**
 * Custom exception class for handling project-specific exceptions.
 * It extends the standard Exception class and provides an option to encapsulate a hidden exception.
 */
public class ProjectException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    private Exception hiddenException;

    /**
     * Constructs a new ProjectException with the specified detail message.
     *
     * @param msg the detail message
     */
    public ProjectException(String msg) {
        super(msg);
    }

    /**
     * Constructs a new ProjectException with the specified detail message and a hidden exception.
     *
     * @param msg the detail message
     * @param e   the hidden exception
     */
    public ProjectException(String msg, Exception e) {
        super(msg);
        hiddenException = e;
    }

    /**
     * Gets the hidden exception encapsulated within this ProjectException.
     *
     * @return the hidden exception, or null if none is set
     */
    public Exception getHiddenException() {
        return hiddenException;
    }
}
