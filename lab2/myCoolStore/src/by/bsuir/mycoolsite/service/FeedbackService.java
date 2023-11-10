package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.bean.Feedback;
import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

public interface FeedbackService {
    public void addFeedback(Feedback feedback) throws ServiceException;
    public List<Feedback> getFilmFeedbacks(long filmId) throws ServiceException;
}
