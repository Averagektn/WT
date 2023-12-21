package by.bsuir.exam.exception;

import java.io.Serial;

public class ProjectException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    private Exception hiddenException;

    public ProjectException(String msg) {
        super(msg);
    }

    public ProjectException(String msg, Exception e) {
        super(msg);
        hiddenException = e;
    }

    public Exception getHiddenException() {
        return hiddenException;
    }
}
