package by.bsuir.mycoolsite.connection.exception;

import java.io.Serial;

public class DBConnectionException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;

    public DBConnectionException() {
        super();
    }

    public DBConnectionException(String message) {
        super(message);
    }

    public DBConnectionException(Exception e) {
        super(e);
    }

    public DBConnectionException(String message, Exception e) {
        super(message, e);
    }
}
