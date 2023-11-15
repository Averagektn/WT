package by.bsuir.mycoolsite.controller.page.exception;

import by.bsuir.mycoolsite.exception.ProjectException;

import java.io.Serial;

public class PageException extends ProjectException {

    @Serial
    private static final long serialVersionUID = 1L;

    public PageException(String msg) {
        super(msg);
    }

    public PageException(String msg, Exception e) {
        super(msg, e);
    }
}
