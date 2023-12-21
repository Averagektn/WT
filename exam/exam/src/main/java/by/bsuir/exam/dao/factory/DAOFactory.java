package by.bsuir.exam.dao.factory;

import by.bsuir.exam.dao.DomDAO;
import by.bsuir.exam.dao.SaxDAO;
import by.bsuir.exam.dao.StaxDAO;
import by.bsuir.exam.dao.impl.DomDAOClass;
import by.bsuir.exam.dao.impl.SaxDAOClass;
import by.bsuir.exam.dao.impl.StaxDAOClass;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final DomDAO domDAO = new DomDAOClass();
    private final SaxDAO saxDAO = new SaxDAOClass();
    private final StaxDAO staxDAO = new StaxDAOClass();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public StaxDAO getFeedbackDAO() {
        return staxDAO;
    }

    public SaxDAO getSaxDAO() {
        return saxDAO;
    }

    public DomDAO getDomDAO() {
        return domDAO;
    }
}
