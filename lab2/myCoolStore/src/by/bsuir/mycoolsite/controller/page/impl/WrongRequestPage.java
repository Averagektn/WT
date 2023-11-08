package by.bsuir.mycoolsite.controller.page.impl;

import by.bsuir.mycoolsite.controller.page.Page;
import by.bsuir.mycoolsite.controller.page.exception.PageException;
import jakarta.servlet.http.HttpServletRequest;

public class WrongRequestPage implements Page {
    @Override
    public String generate(HttpServletRequest request) throws PageException {
        return null;
    }
}
