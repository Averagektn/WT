package by.bsuir.exam.dao.exception;

import by.bsuir.exam.exception.ProjectException;

import java.io.Serial;

public class DAOException extends ProjectException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DAOException(String msg) {
        super(msg);
    }

    public DAOException(String msg, Exception e) {
        super(msg, e);
    }
}