package by.bsuir.mycoolsite.service.impl;

import by.bsuir.mycoolsite.bean.Feedback;
import by.bsuir.mycoolsite.dao.FeedbackDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import by.bsuir.mycoolsite.service.FeedbackService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FeedbackServiceImpl implements FeedbackService {
    private static final Logger logger = LogManager.getLogger(FeedbackServiceImpl.class);

    @Override
    public void addFeedback(Feedback feedback) throws ServiceException {
        if (feedback.getAuthor().getId() < 1) {
            logger.error("Invalid user id in feedback adding");
            throw new ServiceException("Invalid user id in feedback adding");
        }

        if (feedback.getFilm().getId() < 1) {
            logger.error("Invalid film id in feedback adding");
            throw new ServiceException("Invalid film id in feedback adding");
        }

        if (feedback.getText().isEmpty()) {
            logger.error("No feedback text in feedback adding");
            throw new ServiceException("No feedback text in feedback adding");
        }

        if (feedback.getRating() < 0 || feedback.getRating() > 10) {
            logger.error("Invalid rating value in feedback adding");
            throw new ServiceException("Invalid rating value in feedback adding");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        FeedbackDAO feedbackDAO = daoFactory.getFeedbackDAO();

        try {
            feedbackDAO.addFeedback(feedback);
        } catch (DAOException e) {
            logger.error("DAO exception", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Feedback> getFilmFeedbacks(long filmId) throws ServiceException {
        List<Feedback> feedbacks;

        if (filmId < 1) {
            logger.error("Incorrect film ID");
            throw new ServiceException("Incorrect film ID");
        }

        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        FeedbackDAO feedbackDAO = daoObjectFactory.getFeedbackDAO();

        try {
            feedbacks = feedbackDAO.getFilmFeedbacks(filmId);
        } catch (DAOException e) {
            logger.error("DAO Exception", e);
            throw new ServiceException(e);
        }

        return feedbacks;
    }

    @Override
    public void deleteUserFeedbacks(long userId) throws ServiceException {
        if (userId < 1) {
            logger.error("Incorrect user ID");
            throw new ServiceException("Incorrect user ID");
        }

        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        FeedbackDAO feedbackDAO = daoObjectFactory.getFeedbackDAO();

        try {
            feedbackDAO.deleteUserFeedbacks(userId);
        } catch (DAOException e) {
            logger.error("DAO Exception", e);
            throw new ServiceException(e);
        }
    }
}
