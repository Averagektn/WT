package old.service.impl;

import by.bsuir.mycoolsite.dao.AgeRestrictionDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import by.bsuir.mycoolsite.service.AgeRestrictionService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AgeRestrictionServiceImpl implements AgeRestrictionService {
    private static final Logger logger = LogManager.getLogger(AgeRestrictionServiceImpl.class);

    @Override
    public List<String> getAgeRestrictions() throws ServiceException {
        List<String> ageRestrictions;

        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        AgeRestrictionDAO ageRestrictionDAO = daoObjectFactory.getAgeRestrictionDAO();

        try {
            ageRestrictions = ageRestrictionDAO.getAgeRestrictions();
        } catch (DAOException e) {
            logger.error("DAO Exception", e);
            throw new ServiceException(e);
        }

        return ageRestrictions;
    }
}
