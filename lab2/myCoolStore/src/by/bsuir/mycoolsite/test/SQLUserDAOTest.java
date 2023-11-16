package by.bsuir.mycoolsite.test;

import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.dao.UserDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * JUnit test class for the SQLUserDAO implementation.
 */
public class SQLUserDAOTest {

    /**
     * Test the {@code registration} method of SQLUserDAO.
     *
     * @throws DAOException if an exception occurs in the DAO layer.
     */
    @Test
    public void testRegistration() throws DAOException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        User user = new User("testregistration@email.com", "testpassword");
        long id = userDAO.registration(user);

        assertTrue(id > 0);
    }

    /**
     * Test the {@code ban} and {@code isBanned} methods of SQLUserDAO.
     *
     * @throws DAOException if an exception occurs in the DAO layer.
     */
    @Test
    public void testBan() throws DAOException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        userDAO.ban(1, 2);
        assertTrue(userDAO.isBanned(1));
    }

    /**
     * Test the {@code unban} method of SQLUserDAO.
     *
     * @throws DAOException if an exception occurs in the DAO layer.
     */
    @Test
    public void testUnban() throws DAOException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        userDAO.unban(1);
        assertFalse(userDAO.isBanned(1));
    }

    /**
     * Test the {@code getBannedUsers} method of SQLUserDAO.
     *
     * @throws DAOException if an exception occurs in the DAO layer.
     */
    @Test
    public void testGetBannedUsers() throws DAOException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        userDAO.ban(1, 2);
        List<User> bannedUsers = userDAO.getBannedUsers();

        assertNotNull(bannedUsers);
    }
}
