package by.bsuir.exam.service;

import by.bsuir.exam.bean.Data;
import by.bsuir.exam.service.exception.ServiceException;

import java.util.List;

public interface DomService {
    List<Data> getData() throws ServiceException;
}
