package by.bsuir.mycoolsite.dao;

import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.util.List;

public interface AgeRestrictionDAO {
    List<String> getAgeRestrictions() throws DAOException;
}
