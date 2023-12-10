package by.bsuir.mycoolsite.dao;

import by.bsuir.mycoolsite.bean.Category;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.util.List;

/**
 * Data Access Object (DAO) interface for retrieving film categories.
 * Implementations of this interface should provide a method to fetch a list of film categories.
 *
 */
public interface CategoryDAO {

    /**
     * Retrieves a list of film categories.
     *
     * @return a List of Category objects representing film categories
     * @throws DAOException if an issue occurs during the data retrieval process
     */
    List<Category> getCategories() throws DAOException;
}
