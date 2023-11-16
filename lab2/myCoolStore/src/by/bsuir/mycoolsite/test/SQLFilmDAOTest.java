package by.bsuir.mycoolsite.test;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.bean.enums.AgeRestriction;
import by.bsuir.mycoolsite.dao.FilmDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * JUnit test class for the SQLFilmDAO implementation.
 */
public class SQLFilmDAOTest {

    /**
     * Test the {@code getFilms} method of SQLFilmDAO.
     */
    @Test
    public void testGetFilms() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        FilmDAO filmDAO = daoFactory.getFilmDAO();
        try {
            List<Film> films = filmDAO.getFilms();
            assertNotNull(films);
            assertFalse(films.isEmpty());
        } catch (DAOException e) {
            fail("Exception thrown while getting films: " + e.getMessage());
        }
    }

    /**
     * Test the {@code getFilmById} method of SQLFilmDAO.
     */
    @Test
    public void testGetFilmById() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        FilmDAO filmDAO = daoFactory.getFilmDAO();
        long filmId = 1;
        try {
            Film film = filmDAO.getFilmById(filmId);
            assertNotNull(film);
            assertEquals(filmId, film.getId());
        } catch (DAOException e) {
            fail("Exception thrown while getting film by ID: " + e.getMessage());
        }
    }

    /**
     * Test the {@code addFilm} method of SQLFilmDAO.
     */
    @Test
    public void testAddFilm() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        FilmDAO filmDAO = daoFactory.getFilmDAO();
        Film testFilm = new Film("", BigDecimal.ONE, null, 10, "", AgeRestriction.THREE, "", new ArrayList<>());
        try {
            filmDAO.addFilm(testFilm);
        } catch (DAOException e) {
            fail("Exception thrown while adding film: " + e.getMessage());
        }
    }

    /**
     * Test the {@code editFilm} method of SQLFilmDAO.
     */
    @Test
    public void testEditFilm() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        FilmDAO filmDAO = daoFactory.getFilmDAO();
        Film testFilm = new Film("", BigDecimal.ONE, null, 10, "", AgeRestriction.THREE, "", new ArrayList<>());
        try {
            filmDAO.editFilm(testFilm);
        } catch (DAOException e) {
            fail("Exception thrown while editing film: " + e.getMessage());
        }
    }
}

