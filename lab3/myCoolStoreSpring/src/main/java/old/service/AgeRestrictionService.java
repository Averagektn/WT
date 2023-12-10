package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

/**
 * Service interface for managing age restriction-related operations.
 * Implementations of this interface should provide a method for retrieving a list of age restrictions.
 *
 */
public interface AgeRestrictionService {

    /**
     * Retrieves a list of age restrictions.
     *
     * @return a List of String objects representing age restrictions
     * @throws ServiceException if an issue occurs during the service operation
     */
    List<String> getAgeRestrictions() throws ServiceException;
}
