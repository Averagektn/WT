package by.bsuir.mycoolsite.service.impl;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.dao.FilmDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import by.bsuir.mycoolsite.service.FilmService;
import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

public class FilmServiceImpl implements FilmService {
    @Override
    public List<Film> getFilms() throws ServiceException {
        List<Film> films = null;

        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            FilmDAO filmDAO = daoObjectFactory.getFilmDAO();
            films = filmDAO.getFilms();
        } catch (DAOException e) {
            //LOG
            System.out.println("DAOException in FilmServiceImpl " + e.toString());
            throw new ServiceException(e);
        }

        return films;
    }

    @Override
    public void addNewFilm(Film film) throws ServiceException {

    }

    @Override
    public void addEditedFilm(Film film) throws ServiceException {

    }
}
