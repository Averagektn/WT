package by.bsuir.mycoolsite.service.impl;

import by.bsuir.mycoolsite.bean.enums.AgeRestriction;
import by.bsuir.mycoolsite.dao.AgeRestrictionDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import by.bsuir.mycoolsite.service.AgeRestrictionService;
import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

public class AgeRestrictionServiceImpl implements AgeRestrictionService {
    @Override
    public List<String> getAgeRestrictions() throws ServiceException {
        List<String> ageRestrictions;

        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        AgeRestrictionDAO ageRestrictionDAO = daoObjectFactory.getAgeRestrictionDAO();

        try {
            ageRestrictions = ageRestrictionDAO.getAgeRestrictions();
        } catch (DAOException e) {
            //LOG
            System.out.println("DAOException" + e);
            throw new ServiceException(e);
        }

        return ageRestrictions;
    }
}
