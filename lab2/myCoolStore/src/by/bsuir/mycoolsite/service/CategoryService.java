package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.bean.Category;
import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

/**
 * Service interface for managing film category-related operations.
 * Implementations of this interface should provide a method for retrieving a list of film categories.
 *
 */
public interface CategoryService {

    /**
     * Retrieves a list of film categories.
     *
     * @return a List of Category objects representing film categories
     * @throws ServiceException if an issue occurs during the service operation
     */
    List<Category> getCategories() throws ServiceException;
}
