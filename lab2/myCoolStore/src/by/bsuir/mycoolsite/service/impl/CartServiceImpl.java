package by.bsuir.mycoolsite.service.impl;

import by.bsuir.mycoolsite.dao.CartDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import by.bsuir.mycoolsite.service.CartService;
import by.bsuir.mycoolsite.service.exception.ServiceException;

public class CartServiceImpl implements CartService {
    @Override
    public void addFilm(long filmId, long userId) throws ServiceException {
        if (filmId < 1){
            //LOG
            System.out.println("Invalid film id in addFilm");
            throw new ServiceException("Invalid film id in addFilm");
        }

        if (userId < 1){
            //LOG
            System.out.println("Invalid user id in addFilm");
            throw new ServiceException("Invalid user id in addFilm");
        }

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            CartDAO cartDAO = daoFactory.getCartDAO();
            cartDAO.addFilm(filmId, userId);
        } catch (DAOException e) {
            //LOG
            System.out.println("DAO: " + e);
            throw new ServiceException(e);
        }
    }
}
