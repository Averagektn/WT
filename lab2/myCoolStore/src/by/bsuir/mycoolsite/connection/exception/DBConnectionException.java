package by.bsuir.mycoolsite.connection.exception;

import java.io.Serial;

/**
 * Exception class for handling database connection errors.
 */
public class DBConnectionException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new DBConnectionException with no specified detail message.
     */
    public DBConnectionException() {
        super();
    }

    /**
     * Constructs a new DBConnectionException with the specified detail message.
     *
     * @param message The detail message.
     */
    public DBConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new DBConnectionException with the specified cause.
     *
     * @param e The cause.
     */
    public DBConnectionException(Exception e) {
        super(e);
    }

    /**
     * Constructs a new DBConnectionException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param e       The cause.
     */
    public DBConnectionException(String message, Exception e) {
        super(message, e);
    }
}
