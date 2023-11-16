package by.bsuir.mycoolsite.test;

import by.bsuir.mycoolsite.dao.AgeRestrictionDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * JUnit test class for the EnumAgeRestrictionDAO implementation.
 */
public class EnumAgeRestrictionDAOTest {

    /**
     * Tests the {@code getAgeRestrictions} method of the EnumAgeRestrictionDAO.
     */
    @Test
    public void testGetAgeRestrictions() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AgeRestrictionDAO ageRestrictionDAO = daoFactory.getAgeRestrictionDAO();

        try {
            List<String> ageRestrictions = ageRestrictionDAO.getAgeRestrictions();

            assertNotNull(ageRestrictions);
            assertFalse(ageRestrictions.isEmpty());

            assertTrue(ageRestrictions.contains("12+"));
            assertTrue(ageRestrictions.contains("3+"));
            assertTrue(ageRestrictions.contains("21+"));

            assertFalse(ageRestrictions.contains("EMPTY"));
        } catch (DAOException e) {
            fail("Exception thrown while getting age restrictions: " + e.getMessage());
        }
    }
}