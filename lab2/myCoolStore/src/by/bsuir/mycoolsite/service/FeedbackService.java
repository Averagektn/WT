package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.bean.Feedback;
import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

public interface FeedbackService {
    void addFeedback(Feedback feedback) throws ServiceException;

    List<Feedback> getFilmFeedbacks(long filmId) throws ServiceException;

    void deleteUserFeedbacks(long userId) throws ServiceException;
}
