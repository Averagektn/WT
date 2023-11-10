package by.bsuir.mycoolsite.service.impl;

import by.bsuir.mycoolsite.bean.Feedback;
import by.bsuir.mycoolsite.dao.FeedbackDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import by.bsuir.mycoolsite.service.FeedbackService;
import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

public class FeedbackServiceImpl implements FeedbackService {
    @Override
    public void addFeedback(Feedback feedback) throws ServiceException {
        if (feedback.getAuthor().getId() < 1) {
            //LOG
            System.out.println("Invalid user id in feedback adding");
            throw new ServiceException("Invalid user id in feedback adding");
        }

        if (feedback.getFilm().getId() < 1) {
            //LOG
            System.out.println("Invalid film id in feedback adding");
            throw new ServiceException("Invalid film id in feedback adding");
        }

        if (feedback.getText().isEmpty()) {
            //LOG
            System.out.println("No feedback text in feedback adding");
            throw new ServiceException("No feedback text in feedback adding");
        }

        if (feedback.getRating() < 0 || feedback.getRating() > 10) {
            //LOG
            System.out.println("Invalid rating value in feedback adding");
            throw new ServiceException("Invalid rating value in feedback adding");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        FeedbackDAO feedbackDAO = daoFactory.getFeedbackDAO();

        try {
            feedbackDAO.addFeedback(feedback);
        } catch (DAOException e) {
            //LOG
            System.out.println("DAO exception in addFeedback");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Feedback> getFilmFeedbacks(long filmId) throws ServiceException {
        List<Feedback> feedbacks;

        if (filmId < 1) {
            //LOG
            System.out.println("Incorrect film ID");
            throw new ServiceException("Incorrect film ID");
        }

        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        FeedbackDAO feedbackDAO = daoObjectFactory.getFeedbackDAO();

        try {
            feedbacks = feedbackDAO.getFilmFeedbacks(filmId);
        } catch (DAOException e) {
            //LOG
            System.out.println("DAOException in UserServiceImpl " + e);
            throw new ServiceException(e);
        }

        return feedbacks;
    }
}
