package by.bsuir.mycoolsite.dao;

import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.util.List;

/**
 * Data Access Object (DAO) interface for managing user data.
 * Implementations of this interface should provide methods for user sign-in, checking film ownership,
 * checking if a user is banned, user registration, banning and unbanning users, and retrieving a list of banned users.
 *
 */
public interface UserDAO {

    /**
     * Authenticates a user by verifying the provided email and password.
     *
     * @param email    the email of the user
     * @param password the password of the user
     * @return the User object representing the authenticated user
     * @throws DAOException if an issue occurs during the data access process
     */
    User signIn(String email, String password) throws DAOException;

    /**
     * Checks if a user is the owner of a specific film.
     *
     * @param userId the ID of the user
     * @param filmId the ID of the film
     * @return true if the user is the owner, false otherwise
     * @throws DAOException if an issue occurs during the data access process
     */
    boolean isFilmOwner(long userId, long filmId) throws DAOException;

    /**
     * Checks if a user is banned.
     *
     * @param id the ID of the user
     * @return true if the user is banned, false otherwise
     * @throws DAOException if an issue occurs during the data access process
     */
    boolean isBanned(long id) throws DAOException;

    /**
     * Registers a new user.
     *
     * @param user the User object containing registration details
     * @return the ID of the newly registered user
     * @throws DAOException if an issue occurs during the data access or manipulation process
     */
    long registration(User user) throws DAOException;

    /**
     * Bans a user.
     *
     * @param userId  the ID of the user to be banned
     * @param adminId the ID of the administrator performing the action
     * @throws DAOException if an issue occurs during the data access or manipulation process
     */
    void ban(long userId, long adminId) throws DAOException;

    /**
     * Unbans a user.
     *
     * @param userId the ID of the user to be unbanned
     * @throws DAOException if an issue occurs during the data access or manipulation process
     */
    void unban(long userId) throws DAOException;

    /**
     * Retrieves a list of banned users.
     *
     * @return a List of User objects representing banned users
     * @throws DAOException if an issue occurs during the data access process
     */
    List<User> getBannedUsers() throws DAOException;
}
