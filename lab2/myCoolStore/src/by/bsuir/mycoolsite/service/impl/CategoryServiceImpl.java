package by.bsuir.mycoolsite.service.impl;

import by.bsuir.mycoolsite.bean.Category;
import by.bsuir.mycoolsite.dao.CategoryDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import by.bsuir.mycoolsite.service.CategoryService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger = LogManager.getLogger(CategoryServiceImpl.class);

    @Override
    public List<Category> getCategories() throws ServiceException {
        List<Category> categories;

        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        CategoryDAO categoryDAO = daoObjectFactory.getCategoryDAO();

        try {
            categories = categoryDAO.getCategories();
        } catch (DAOException e) {
            logger.error("DAO Exception", e);
            throw new ServiceException(e);
        }

        return categories;
    }
}
