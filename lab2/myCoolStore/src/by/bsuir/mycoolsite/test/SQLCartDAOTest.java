package by.bsuir.mycoolsite.test;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.dao.CartDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * JUnit test class for the SQLCartDAO implementation.
 */
public class SQLCartDAOTest {

    /**
     * Test the {@code addFilm} method of SQLCartDAO.
     */
    @Test
    public void testAddFilm() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        CartDAO cartDAO = daoFactory.getCartDAO();
        long filmId = 1;
        long userId = 1;
        try {
            cartDAO.addFilm(filmId, userId);
        } catch (DAOException e) {
            fail("Exception thrown while adding film to cart: " + e.getMessage());
        }
    }

    /**
     * Test the {@code getCart} method of SQLCartDAO.
     */
    @Test
    public void testGetCart() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        CartDAO cartDAO = daoFactory.getCartDAO();
        long userId = 1;
        try {
            List<Film> cartFilms = cartDAO.getCart(userId);
            assertNotNull(cartFilms);
        } catch (DAOException e) {
            fail("Exception thrown while getting cart contents: " + e.getMessage());
        }
    }

    /**
     * Test the {@code remove} method of SQLCartDAO.
     */
    @Test
    public void testRemove() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        CartDAO cartDAO = daoFactory.getCartDAO();
        long filmId = 1;
        long userId = 1;
        try {
            cartDAO.remove(filmId, userId);
        } catch (DAOException e) {
            fail("Exception thrown while removing film from cart: " + e.getMessage());
        }
    }

    /**
     * Test the {@code clear} method of SQLCartDAO.
     */
    @Test
    public void testClear() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        CartDAO cartDAO = daoFactory.getCartDAO();
        long userId = 1;
        try {
            cartDAO.clear(userId);
        } catch (DAOException e) {
            fail("Exception thrown while clearing cart: " + e.getMessage());
        }
    }

    /**
     * Test the {@code contains} method of SQLCartDAO.
     */
    @Test
    public void testContains() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        CartDAO cartDAO = daoFactory.getCartDAO();
        long filmId = 1;
        long userId = 1;
        try {
            boolean contains = cartDAO.contains(userId, filmId);
            assertTrue(contains);
        } catch (DAOException e) {
            fail("Exception thrown while checking if the film is in the cart: " + e.getMessage());
        }
    }
}
