package by.bsuir.mycoolsite.dao.impl;

import by.bsuir.mycoolsite.bean.enums.AgeRestriction;
import by.bsuir.mycoolsite.dao.AgeRestrictionDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.util.ArrayList;
import java.util.List;

public class EnumAgeRestrictionDAO implements AgeRestrictionDAO {
    @Override
    public List<String> getAgeRestrictions() throws DAOException {
        List<String> ageRestrictions = new ArrayList<>();

        for (AgeRestriction ageRestriction : AgeRestriction.values()) {
            ageRestrictions.add(ageRestriction.toString());
        }
        ageRestrictions.remove(AgeRestriction.EMPTY.toString());

        return ageRestrictions;
    }
}
