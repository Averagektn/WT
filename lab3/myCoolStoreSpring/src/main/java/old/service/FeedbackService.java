package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.bean.Feedback;
import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

/**
 * Service interface for managing film feedback-related operations.
 * Implementations of this interface should provide methods for adding new feedback for a film,
 * retrieving feedback for a film, and deleting feedback submitted by a user.
 *
 */
public interface FeedbackService {

    /**
     * Adds new feedback for a film.
     *
     * @param feedback the Feedback object containing the feedback details
     * @throws ServiceException if an issue occurs during the service operation
     */
    void addFeedback(Feedback feedback) throws ServiceException;

    /**
     * Retrieves a list of feedback for a specific film.
     *
     * @param filmId the ID of the film
     * @return a List of Feedback objects representing feedback for the film
     * @throws ServiceException if an issue occurs during the service operation
     */
    List<Feedback> getFilmFeedbacks(long filmId) throws ServiceException;

    /**
     * Deletes all feedback submitted by a specific user.
     *
     * @param userId the ID of the user
     * @throws ServiceException if an issue occurs during the service operation
     */
    void deleteUserFeedbacks(long userId) throws ServiceException;
}
