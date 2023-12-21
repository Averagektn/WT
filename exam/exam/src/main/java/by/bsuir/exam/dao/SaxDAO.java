package by.bsuir.exam.dao;

import by.bsuir.exam.bean.Data;
import by.bsuir.exam.dao.exception.DAOException;

public interface SaxDAO {
    Data getData() throws DAOException;
}
