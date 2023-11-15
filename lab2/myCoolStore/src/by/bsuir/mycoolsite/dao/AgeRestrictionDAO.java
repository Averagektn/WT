package by.bsuir.mycoolsite.dao;

import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.util.List;

/**
 * Data Access Object (DAO) interface for retrieving age restrictions.
 * Implementations of this interface should provide a method to fetch a list of age restrictions.
 *
 */
public interface AgeRestrictionDAO {

    /**
     * Retrieves a list of age restrictions.
     *
     * @return a List of String representing age restrictions
     * @throws DAOException if an issue occurs during the data retrieval process
     */
    List<String> getAgeRestrictions() throws DAOException;
}
