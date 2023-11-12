package by.bsuir.mycoolsite.service.impl;

import by.bsuir.mycoolsite.bean.Category;
import by.bsuir.mycoolsite.dao.CategoryDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import by.bsuir.mycoolsite.service.CategoryService;
import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<Category> getCategories() throws ServiceException {
        List<Category> categories;

        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        CategoryDAO categoryDAO = daoObjectFactory.getCategoryDAO();

        try {
            categories = categoryDAO.getCategories();
        } catch (DAOException e) {
            //LOG
            System.out.println("DAOException " + e);
            throw new ServiceException(e);
        }

        return categories;
    }
}
