package by.bsuir.mycoolsite.dao;

import by.bsuir.mycoolsite.bean.Category;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.util.List;

public interface CategoryDAO {
    List<Category> getCategories() throws DAOException;
}
