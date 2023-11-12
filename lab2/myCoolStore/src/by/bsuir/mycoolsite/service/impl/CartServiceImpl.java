package by.bsuir.mycoolsite.service.impl;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.dao.CartDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import by.bsuir.mycoolsite.service.CartService;
import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

public class CartServiceImpl implements CartService {
    @Override
    public void addFilm(long filmId, long userId) throws ServiceException {
        if (filmId < 1) {
            //LOG
            System.out.println("Invalid film id in addFilm");
            throw new ServiceException("Invalid film id in addFilm");
        }

        if (userId < 1) {
            //LOG
            System.out.println("Invalid user id in addFilm");
            throw new ServiceException("Invalid user id in addFilm");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        CartDAO cartDAO = daoFactory.getCartDAO();

        try {
            cartDAO.addFilm(filmId, userId);
        } catch (DAOException e) {
            //LOG
            System.out.println("DAO: " + e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Film> getCart(long userId) throws ServiceException {
        List<Film> cartFilms;

        if (userId < 1) {
            //LOG
            System.out.println("Invalid user id in getCart");
            throw new ServiceException("Invalid user id in getCart");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        CartDAO cartDAO = daoFactory.getCartDAO();

        try {
            cartFilms = cartDAO.getCart(userId);
        } catch (DAOException e) {
            //LOG
            System.out.println("DAO: " + e);
            throw new ServiceException(e);
        }

        return cartFilms;
    }

    @Override
    public void remove(long filmId, long userId) throws ServiceException {
        if (filmId < 1) {
            //LOG
            System.out.println("Invalid film id in remove");
            throw new ServiceException("Invalid film id in remove");
        }

        if (userId < 1) {
            //LOG
            System.out.println("Invalid user id in remove");
            throw new ServiceException("Invalid user id in remove");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        CartDAO cartDAO = daoFactory.getCartDAO();

        try {
            cartDAO.remove(filmId, userId);
        } catch (DAOException e) {
            //LOG
            System.out.println("DAO: " + e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void clear(long userId) throws ServiceException {
        if (userId < 1) {
            //LOG
            System.out.println("Invalid user id in clear");
            throw new ServiceException("Invalid user id in clear");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        CartDAO cartDAO = daoFactory.getCartDAO();

        try {
            cartDAO.clear(userId);
        } catch (DAOException e) {
            //LOG
            System.out.println("DAO: " + e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean contains(long userId, long filmId) throws ServiceException {
        boolean contains;

        if (filmId < 1) {
            //LOG
            System.out.println("Invalid film id in contains");
            throw new ServiceException("Invalid film id in contains");
        }

        if (userId < 1) {
            //LOG
            System.out.println("Invalid user id in contains");
            throw new ServiceException("Invalid user id in contains");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        CartDAO cartDAO = daoFactory.getCartDAO();

        try {
            contains = cartDAO.contains(userId, filmId);
        } catch (DAOException e) {
            //LOG
            System.out.println("DAO: " + e);
            throw new ServiceException(e);
        }

        return contains;
    }
}
