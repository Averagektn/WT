package by.bsuir.mycoolsite.service.impl;

import by.bsuir.mycoolsite.bean.Feedback;
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
        List<Film> films;

        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            FilmDAO filmDAO = daoObjectFactory.getFilmDAO();
            films = filmDAO.getFilms();
        } catch (DAOException e) {
            //LOG
            System.out.println("DAOException in FilmServiceImpl " + e);
            throw new ServiceException(e);
        }

        return films;
    }

    @Override
    public Film getFilmById(long id) throws ServiceException {
        Film film;

        if (id < 1){
            //LOG
            System.out.println("Incorrect film ID");
            throw new ServiceException("Incorrect film ID");
        }

        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            FilmDAO filmDAO = daoObjectFactory.getFilmDAO();
            film = filmDAO.getFilmById(id);
        } catch (DAOException e) {
            //LOG
            System.out.println("DAOException in UserServiceImpl " + e);
            throw new ServiceException(e);
        }

        return film;
    }

    @Override
    public List<Feedback> getFilmFeedbacks(long filmId) throws ServiceException {
        List<Feedback> feedbacks;

        if (filmId < 1){
            //LOG
            System.out.println("Incorrect film ID");
            throw new ServiceException("Incorrect film ID");
        }

        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            FilmDAO filmDAO = daoObjectFactory.getFilmDAO();
            feedbacks = filmDAO.getFilmFeedbacks(filmId);
        } catch (DAOException e) {
            //LOG
            System.out.println("DAOException in UserServiceImpl " + e);
            throw new ServiceException(e);
        }

        return feedbacks;
    }

    @Override
    public void addNewFilm(Film film) throws ServiceException {

    }

    @Override
    public void addEditedFilm(Film film) throws ServiceException {

    }
}
