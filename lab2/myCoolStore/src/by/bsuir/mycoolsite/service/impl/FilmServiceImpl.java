package by.bsuir.mycoolsite.service.impl;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.bean.enums.AgeRestriction;
import by.bsuir.mycoolsite.dao.FilmDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import by.bsuir.mycoolsite.service.FilmService;
import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;

public class FilmServiceImpl implements FilmService {
    @Override
    public List<Film> getFilms() throws ServiceException {
        List<Film> films;

        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        FilmDAO filmDAO = daoObjectFactory.getFilmDAO();

        try {
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

        if (id < 1) {
            //LOG
            System.out.println("Incorrect film ID");
            throw new ServiceException("Incorrect film ID");
        }

        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        FilmDAO filmDAO = daoObjectFactory.getFilmDAO();

        try {
            film = filmDAO.getFilmById(id);
        } catch (DAOException e) {
            //LOG
            System.out.println("DAOException in UserServiceImpl " + e);
            throw new ServiceException(e);
        }

        return film;
    }

    @Override
    public void addNewFilm(Film film) throws ServiceException {
        if (film.getDescription().isEmpty()){
            throw new ServiceException("");
        }

        if (film.getPrice().compareTo(BigDecimal.ZERO) < 0){
            throw new ServiceException("");
        }

        if (film.getDiscount() < 0 || film.getDiscount() > 100){
            throw new ServiceException("");
        }

        if (film.getAuthor().isEmpty()){
            throw new ServiceException("");
        }

        if (film.getName().isEmpty()){
            throw new ServiceException("");
        }

        if (film.getAgeRestriction() == AgeRestriction.EMPTY){
            throw new ServiceException("");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        FilmDAO filmDAO = daoFactory.getFilmDAO();

        try {
            filmDAO.addFilm(film);
        } catch (DAOException e) {
            throw new ServiceException("DAO failed: " + e);
        }
    }

    @Override
    public void editFilm(Film film) throws ServiceException {

    }
}
