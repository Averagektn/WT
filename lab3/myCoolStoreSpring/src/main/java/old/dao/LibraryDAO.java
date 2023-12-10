package by.bsuir.mycoolsite.dao;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.util.List;

/**
 * Data Access Object (DAO) interface for managing user film libraries.
 * Implementations of this interface should provide methods for adding a film to a user's library
 * and retrieving the list of films in a user's library.
 *
 */
public interface LibraryDAO {

    /**
     * Adds a film to the user's film library.
     *
     * @param userId the ID of the user
     * @param filmId the ID of the film to be added
     * @throws DAOException if an issue occurs during the data access or manipulation process
     */
    void addFilm(long userId, long filmId) throws DAOException;

    /**
     * Retrieves the list of films in a user's film library.
     *
     * @param userId the ID of the user
     * @return a List of Film objects representing films in the user's library
     * @throws DAOException if an issue occurs during the data access process
     */
    List<Film> getUserFilms(long userId) throws DAOException;
}
