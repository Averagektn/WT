package by.bsuir.mycoolsite.dao;

import by.bsuir.mycoolsite.bean.Feedback;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.util.List;

public interface FeedbackDAO {
    List<Feedback> getFilmFeedbacks(long filmId) throws DAOException;

    void addFeedback(Feedback feedback) throws DAOException;

    void deleteUserFeedbacks(long userId) throws DAOException;
}
