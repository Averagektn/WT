package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

/**
 * Service interface for managing user-related operations.
 * Implementations of this interface should provide methods for user sign-in, checking film ownership,
 * checking if a user is banned, user registration, banning and unbanning users, and retrieving a list of banned users.
 *
 */
public interface UserService {

    /**
     * Authenticates a user by verifying the provided email and password.
     *
     * @param email    the email of the user
     * @param password the password of the user
     * @return the User object representing the authenticated user
     * @throws ServiceException if an issue occurs during the service operation
     */
    User signIn(String email, String password) throws ServiceException;

    /**
     * Checks if a user is the owner of a specific film.
     *
     * @param userId the ID of the user
     * @param filmId the ID of the film
     * @return true if the user is the owner, false otherwise
     * @throws ServiceException if an issue occurs during the service operation
     */
    boolean isFilmOwner(long userId, long filmId) throws ServiceException;

    /**
     * Checks if a user is banned.
     *
     * @param id the ID of the user
     * @return true if the user is banned, false otherwise
     * @throws ServiceException if an issue occurs during the service operation
     */
    boolean isBanned(long id) throws ServiceException;

    /**
     * Registers a new user.
     *
     * @param user the User object containing registration details
     * @return the ID of the newly registered user
     * @throws ServiceException if an issue occurs during the service operation
     */
    long registration(User user) throws ServiceException;

    /**
     * Bans a user.
     *
     * @param userId  the ID of the user to be banned
     * @param adminId the ID of the administrator performing the action
     * @throws ServiceException if an issue occurs during the service operation
     */
    void ban(long userId, long adminId) throws ServiceException;

    /**
     * Unbans a user.
     *
     * @param userId the ID of the user to be unbanned
     * @throws ServiceException if an issue occurs during the service operation
     */
    void unban(long userId) throws ServiceException;

    /**
     * Retrieves a list of banned users.
     *
     * @return a List of User objects representing banned users
     * @throws ServiceException if an issue occurs during the service operation
     */
    List<User> getBannedUsers() throws ServiceException;
}
