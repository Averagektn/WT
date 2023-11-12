package by.bsuir.mycoolsite.dao;

import by.bsuir.mycoolsite.bean.enums.AgeRestriction;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.util.List;

public interface AgeRestrictionDAO {
    List<AgeRestriction> getAgeRestrictions() throws DAOException;
}
