package by.bsuir.exam.dao;

import by.bsuir.exam.bean.Data;
import by.bsuir.exam.dao.exception.DAOException;

import java.util.List;

public interface SaxDAO {
    List<Data> getData() throws DAOException;
}
