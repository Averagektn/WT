package by.bsuir.mycoolsite.test;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.dao.LibraryDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * JUnit test class for the SQLLibraryDAO implementation.
 */
public class SQLLibraryDAOTest {

    /**
     * Test the {@code addFilm} and {@code getUserFilms} methods of SQLLibraryDAO.
     *
     * @throws DAOException if an exception occurs in the DAO layer.
     */
    @Test
    public void testAddFilm() throws DAOException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        LibraryDAO libraryDAO = daoFactory.getLibraryDAO();
        long userId = 1;
        long filmId = 2;

        libraryDAO.addFilm(userId, filmId);
        List<Film> films = libraryDAO.getUserFilms(userId);

        assertNotNull(films);
    }
}
