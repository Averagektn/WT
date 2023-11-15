package by.bsuir.mycoolsite.dao;

import by.bsuir.mycoolsite.bean.Feedback;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.util.List;

/**
 * Data Access Object (DAO) interface for managing film feedback.
 * Implementations of this interface should provide methods for retrieving feedback for a film,
 * adding new feedback, and deleting feedback submitted by a user.
 *
 */
public interface FeedbackDAO {

    /**
     * Retrieves a list of feedback for a specific film.
     *
     * @param filmId the ID of the film
     * @return a List of Feedback objects representing feedback for the film
     * @throws DAOException if an issue occurs during the data access process
     */
    List<Feedback> getFilmFeedbacks(long filmId) throws DAOException;

    /**
     * Adds new feedback for a film.
     *
     * @param feedback the Feedback object containing the feedback details
     * @throws DAOException if an issue occurs during the data access or manipulation process
     */
    void addFeedback(Feedback feedback) throws DAOException;

    /**
     * Deletes all feedback submitted by a specific user.
     *
     * @param userId the ID of the user
     * @throws DAOException if an issue occurs during the data access or manipulation process
     */
    void deleteUserFeedbacks(long userId) throws DAOException;
}
