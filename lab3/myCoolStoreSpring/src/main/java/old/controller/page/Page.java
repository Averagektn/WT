package by.bsuir.mycoolsite.controller.page;

import by.bsuir.mycoolsite.controller.page.exception.PageException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Interface for page generation.
 */
public interface Page {
    /**
     * Generates a response based on the provided HttpServletRequest.
     *
     * @param request HttpServletRequest object
     * @return String representing the generated response
     * @throws PageException if an error occurs during page generation
     */
    String generate(HttpServletRequest request) throws PageException;
}

