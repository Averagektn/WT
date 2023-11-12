package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.bean.Category;
import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories() throws ServiceException;
}
