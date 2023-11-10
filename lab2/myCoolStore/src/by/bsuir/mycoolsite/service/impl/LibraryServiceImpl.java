package by.bsuir.mycoolsite.service.impl;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.dao.LibraryDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import by.bsuir.mycoolsite.service.LibraryService;
import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

public class LibraryServiceImpl implements LibraryService {
    @Override
    public void addFilm(long userId, long filmId) throws ServiceException {
        if (userId < 1){
            //LOG
            System.out.println("Invalid user ID in addFilm");
            throw new ServiceException("Invalid user ID in addFilm");
        }

        if (filmId < 1){
            //LOG
            System.out.println("Invalid film ID in addFilm");
            throw new ServiceException("Invalid film ID in addFilm");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        LibraryDAO libraryDAO = daoFactory.getLibraryDAO();

        try{
            libraryDAO.addFilm(userId, filmId);
        } catch (DAOException e) {
            //LOG
            System.out.println("DAO: " + e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Film> getUserFilms(long userId) throws ServiceException {
        List<Film> films;

        if (userId < 1){
            //LOG
            System.out.println("Invalid user ID in getUserFilms");
            throw new ServiceException("Invalid user ID in getUserFilms");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        LibraryDAO libraryDAO = daoFactory.getLibraryDAO();

        try{
            films = libraryDAO.getUserFilms(userId);
        } catch (DAOException e) {
            //LOG
            System.out.println("DAO: " + e);
            throw new ServiceException(e);
        }

        return films;
    }
}
