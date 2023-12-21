package by.bsuir.exam.service.exception;

import by.bsuir.exam.exception.ProjectException;

import java.io.Serial;

public class ServiceException extends ProjectException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Exception e) {
        super(msg, e);
    }
}