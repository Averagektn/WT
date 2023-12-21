package by.bsuir.exam.service;

import by.bsuir.exam.bean.Data;
import by.bsuir.exam.service.exception.ServiceException;

public interface DomService {
    Data getData() throws ServiceException;
}
