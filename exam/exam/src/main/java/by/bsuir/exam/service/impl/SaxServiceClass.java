package by.bsuir.exam.service.impl;

import by.bsuir.exam.bean.Data;
import by.bsuir.exam.dao.exception.DAOException;
import by.bsuir.exam.dao.factory.DAOFactory;
import by.bsuir.exam.service.SaxService;
import by.bsuir.exam.service.exception.ServiceException;

import java.util.List;

public class SaxServiceClass implements SaxService {
    @Override
    public List<Data> getData() throws ServiceException {
        List<Data> data;

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
