package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

/**
 * Service interface for managing film-related operations.
 * Implementations of this interface should provide methods for retrieving a list of films,
 * getting a film by its ID, adding a new film, and editing existing film details.
 *
 */
public interface FilmService {

    /**
     * Retrieves a list of all films.
     *
     * @return a List of Film objects representing all films
     * @throws ServiceException if an issue occurs during the service operation
     */
    List<Film> getFilms() throws ServiceException;

    /**
     * Retrieves a specific film by its ID.
     *
     * @param id the ID of the film
     * @return the Film object representing the specified film
     * @throws ServiceException if an issue occurs during the service operation
     */
    Film getFilmById(long id) throws ServiceException;

    /**
     * Adds a new film to the database.
     *
     * @param film the Film object containing the details of the new film
     * @throws ServiceException if an issue occurs during the service operation
     */
    void addNewFilm(Film film) throws ServiceException;

    /**
     * Edits the details of an existing film.
     *
     * @param film the Film object containing the updated details
     * @throws ServiceException if an issue occurs during the service operation
     */
    void editFilm(Film film) throws ServiceException;
}
