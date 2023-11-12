package by.bsuir.mycoolsite.dao.impl;

import by.bsuir.mycoolsite.bean.enums.AgeRestriction;
import by.bsuir.mycoolsite.dao.AgeRestrictionDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.util.ArrayList;
import java.util.List;

public class EnumAgeRestrictionDAO implements AgeRestrictionDAO {
    @Override
    public List<AgeRestriction> getAgeRestrictions() throws DAOException {
        List<AgeRestriction> ageRestrictions = new ArrayList<>(List.of(AgeRestriction.values()));

        ageRestrictions.remove(AgeRestriction.EMPTY);

        return ageRestrictions;
    }
}
