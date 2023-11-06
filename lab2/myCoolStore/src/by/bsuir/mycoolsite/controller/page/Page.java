package by.bsuir.mycoolsite.controller.page;

import by.bsuir.mycoolsite.controller.page.exception.PageException;
import jakarta.servlet.http.HttpServletRequest;

public interface Page {
    public String generate(HttpServletRequest request) throws PageException;
}
