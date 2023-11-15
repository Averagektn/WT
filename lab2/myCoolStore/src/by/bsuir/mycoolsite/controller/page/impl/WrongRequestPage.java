package by.bsuir.mycoolsite.controller.page.impl;

import by.bsuir.mycoolsite.controller.page.Page;
import by.bsuir.mycoolsite.controller.page.exception.PageException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Implementation of the Page interface for handling wrong requests.
 */
public class WrongRequestPage implements Page {
    /**
     * {@inheritDoc}
     */
    @Override
    public String generate(HttpServletRequest request) throws PageException {
        // Implementation can be added based on how you want to handle wrong requests
        return null;
    }
}
