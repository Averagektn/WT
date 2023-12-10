package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

/**
 * Service interface for managing user film libraries.
 * Implementations of this interface should provide methods for adding a film to a user's library
 * and retrieving the list of films in a user's library.
 *
 */
public interface LibraryService {

    /**
     * Adds a film to the user's film library.
     *
     * @param userId the ID of the user
     * @param filmId the ID of the film to be added
     * @throws ServiceException if an issue occurs during the service operation
     */
    void addFilm(long userId, long filmId) throws ServiceException;

    /**
     * Retrieves the list of films in a user's film library.
     *
     * @param userId the ID of the user
     * @return a List of Film objects representing films in the user's library
     * @throws ServiceException if an issue occurs during the service operation
     */
    List<Film> getUserFilms(long userId) throws ServiceException;
}
