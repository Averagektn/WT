package by.bsuir.exam.service.impl;

import by.bsuir.exam.bean.Data;
import by.bsuir.exam.dao.exception.DAOException;
import by.bsuir.exam.dao.factory.DAOFactory;
import by.bsuir.exam.service.DomService;
import by.bsuir.exam.service.exception.ServiceException;

public class DomServiceClass implements DomService {
    @Override
    public Data getData() throws ServiceException {
        Data data;

        var dao = DAOFactory.getInstance();
        var domDAO = dao.getDomDAO();

        try {
            data = domDAO.getData();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

        return data;
    }
}
