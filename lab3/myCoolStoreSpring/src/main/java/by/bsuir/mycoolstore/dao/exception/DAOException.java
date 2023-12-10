package by.bsuir.mycoolstore.dao.exception;

import java.io.Serial;

/**
 * Exception class for Data Access Object (DAO) related issues.
 */
public class DAOException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new DAOException with no detail message.
     */
    public DAOException() {
        super();
    }

    /**
     * Constructs a new DAOException with the specified detail message.
     *
     * @param message the detail message
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Constructs a new DAOException with the specified cause.
     *
     * @param e the cause (which is saved for later retrieval by the getCause() method)
     */
    public DAOException(Exception e) {
        super(e);
    }

    /**
     * Constructs a new DAOException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param e       the cause (which is saved for later retrieval by the getCause() method)
     */
    public DAOException(String message, Exception e) {
        super(message, e);
    }
}
