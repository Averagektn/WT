package by.bsuir.mycoolsite.test;

import by.bsuir.mycoolsite.bean.Feedback;
import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.dao.FeedbackDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * JUnit test class for the SQLFeedbackDAO implementation.
 */
public class SQLFeedbackDAOTest {

    /**
     * Test the {@code getFilmFeedbacks} method of SQLFeedbackDAO.
     */
    @Test
    public void testGetFilmFeedbacks() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        FeedbackDAO feedbackDAO = daoFactory.getFeedbackDAO();
        long filmId = 1;
        try {
            List<Feedback> feedbacks = feedbackDAO.getFilmFeedbacks(filmId);
            assertNotNull(feedbacks);
        } catch (DAOException e) {
            fail("Exception thrown while getting film feedbacks: " + e.getMessage());
        }
    }

    /**
     * Test the {@code addFeedback} method of SQLFeedbackDAO.
     */
    @Test
    public void testAddFeedback() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        FeedbackDAO feedbackDAO = daoFactory.getFeedbackDAO();
        Feedback testFeedback = new Feedback(new User(1), new Film(2), "", 5);
        try {
            feedbackDAO.addFeedback(testFeedback);
        } catch (DAOException e) {
            fail("Exception thrown while adding feedback: " + e.getMessage());
        }
    }

    /**
     * Test the {@code deleteUserFeedbacks} method of SQLFeedbackDAO.
     */
    @Test
    public void testDeleteUserFeedbacks() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        FeedbackDAO feedbackDAO = daoFactory.getFeedbackDAO();
        long userId = 1;
        try {
            feedbackDAO.deleteUserFeedbacks(userId);
        } catch (DAOException e) {
            fail("Exception thrown while deleting user feedbacks: " + e.getMessage());
        }
    }
}
