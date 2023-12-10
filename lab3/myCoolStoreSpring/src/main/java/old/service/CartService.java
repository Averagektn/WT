package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

/**
 * Service interface for managing user shopping cart-related operations.
 * Implementations of this interface should provide methods for adding a film to the cart,
 * retrieving the contents of the cart, removing a film from the cart, clearing the cart,
 * and checking if the cart contains a specific film.
 *
 */
public interface CartService {

    /**
     * Adds a film to the user's shopping cart.
     *
     * @param filmId the ID of the film to be added to the cart
     * @param userId the ID of the user
     * @throws ServiceException if an issue occurs during the service operation
     */
    void addFilm(long filmId, long userId) throws ServiceException;

    /**
     * Retrieves the contents of the user's shopping cart.
     *
     * @param userId the ID of the user
     * @return a List of Film objects representing the films in the cart
     * @throws ServiceException if an issue occurs during the service operation
     */
    List<Film> getCart(long userId) throws ServiceException;

    /**
     * Removes a film from the user's shopping cart.
     *
     * @param filmId the ID of the film to be removed
     * @param userId the ID of the user
     * @throws ServiceException if an issue occurs during the service operation
     */
    void remove(long filmId, long userId) throws ServiceException;

    /**
     * Clears the user's shopping cart.
     *
     * @param userId the ID of the user
     * @throws ServiceException if an issue occurs during the service operation
     */
    void clear(long userId) throws ServiceException;

    /**
     * Checks if the user's shopping cart contains a specific film.
     *
     * @param userId the ID of the user
     * @param filmId the ID of the film
     * @return true if the cart contains the film, false otherwise
     * @throws ServiceException if an issue occurs during the service operation
     */
    boolean contains(long userId, long filmId) throws ServiceException;
}
