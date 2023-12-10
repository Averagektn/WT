package by.bsuir.mycoolsite.dao;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.util.List;

/**
 * Data Access Object (DAO) interface for managing user shopping carts.
 * Implementations of this interface should provide methods for adding, retrieving, removing,
 * clearing, and checking the presence of films in a user's shopping cart.
 *
 */
public interface CartDAO {

    /**
     * Adds a film to the user's shopping cart.
     *
     * @param filmId the ID of the film to be added
     * @param userId the ID of the user
     * @throws DAOException if an issue occurs during the data access or manipulation process
     */
    void addFilm(long filmId, long userId) throws DAOException;

    /**
     * Retrieves the list of films in the user's shopping cart.
     *
     * @param userId the ID of the user
     * @return a List of Film objects representing films in the cart
     * @throws DAOException if an issue occurs during the data access process
     */
    List<Film> getCart(long userId) throws DAOException;

    /**
     * Removes a film from the user's shopping cart.
     *
     * @param filmId the ID of the film to be removed
     * @param userId the ID of the user
     * @throws DAOException if an issue occurs during the data access or manipulation process
     */
    void remove(long filmId, long userId) throws DAOException;

    /**
     * Clears all films from the user's shopping cart.
     *
     * @param userId the ID of the user
     * @throws DAOException if an issue occurs during the data access or manipulation process
     */
    void clear(long userId) throws DAOException;

    /**
     * Checks if a film is present in the user's shopping cart.
     *
     * @param userId the ID of the user
     * @param filmId the ID of the film to check
     * @return true if the film is in the cart, false otherwise
     * @throws DAOException if an issue occurs during the data access process
     */
    boolean contains(long userId, long filmId) throws DAOException;
}
