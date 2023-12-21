package by.bsuir.exam.service.impl;

import by.bsuir.exam.bean.Data;
import by.bsuir.exam.dao.exception.DAOException;
import by.bsuir.exam.dao.factory.DAOFactory;
import by.bsuir.exam.service.SaxService;
import by.bsuir.exam.service.exception.ServiceException;

public class SaxServiceClass implements SaxService {
    @Override
    public Data getData() throws ServiceException {
        Data data;

        var dao = DAOFactory.getInstance();
        var saxDAO = dao.getSaxDAO();

        try {
            data = saxDAO.getData();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

        return data;
    }
}
