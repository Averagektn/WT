package by.bsuir.mycoolsite.dao;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.util.List;

/**
 * Data Access Object (DAO) interface for managing film data.
 * Implementations of this interface should provide methods for retrieving a list of films,
 * getting a film by its ID, adding a new film, and editing existing film details.
 *
 */
public interface FilmDAO {

    /**
     * Retrieves a list of all films.
     *
     * @return a List of Film objects representing all films
     * @throws DAOException if an issue occurs during the data access process
     */
    List<Film> getFilms() throws DAOException;

    /**
     * Retrieves a specific film by its ID.
     *
     * @param id the ID of the film
     * @return the Film object representing the specified film
     * @throws DAOException if an issue occurs during the data access process
     */
    Film getFilmById(long id) throws DAOException;

    /**
     * Adds a new film to the database.
     *
     * @param film the Film object containing the details of the new film
     * @throws DAOException if an issue occurs during the data access or manipulation process
     */
    void addFilm(Film film) throws DAOException;

    /**
     * Edits the details of an existing film.
     *
     * @param film the Film object containing the updated details
     * @throws DAOException if an issue occurs during the data access or manipulation process
     */
    void editFilm(Film film) throws DAOException;
}
