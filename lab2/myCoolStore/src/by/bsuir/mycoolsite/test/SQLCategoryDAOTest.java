package by.bsuir.mycoolsite.test;

import by.bsuir.mycoolsite.bean.Category;
import by.bsuir.mycoolsite.dao.CategoryDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import org.testng.annotations.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * JUnit test class for the SQLCategoryDAO implementation.
 */
public class SQLCategoryDAOTest {

    /**
     * Test the {@code getCategories} method of SQLCategoryDAO.
     */
    @Test
    public void testGetCategories() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        CategoryDAO categoryDAO = daoFactory.getCategoryDAO();
        try {
            List<Category> categories = categoryDAO.getCategories();
            assertNotNull(categories);
        } catch (DAOException e) {
            fail("Exception thrown while getting categories: " + e.getMessage());
        }
    }
}