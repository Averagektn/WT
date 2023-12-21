package by.bsuir.exam.service.impl;

import by.bsuir.exam.bean.Data;
import by.bsuir.exam.dao.exception.DAOException;
import by.bsuir.exam.dao.factory.DAOFactory;
import by.bsuir.exam.service.StaxService;
import by.bsuir.exam.service.exception.ServiceException;

public class StaxServiceClass implements StaxService {
    @Override
    public Data getData() throws ServiceException {
        Data data;

        var dao = DAOFactory.getInstance();
        var staxDAO = dao.getStaxDAO();

        try {
            data = staxDAO.getData();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

        return data;
    }
}
