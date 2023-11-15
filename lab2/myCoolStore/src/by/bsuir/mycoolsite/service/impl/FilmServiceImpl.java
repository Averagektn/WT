package by.bsuir.mycoolsite.service.impl;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.bean.enums.AgeRestriction;
import by.bsuir.mycoolsite.dao.FilmDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import by.bsuir.mycoolsite.service.FilmService;
import by.bsuir.mycoolsite.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public class FilmServiceImpl implements FilmService {
    private static final Logger logger = LogManager.getLogger(FilmServiceImpl.class);

    @Override
    public List<Film> getFilms() throws ServiceException {
        List<Film> films;

        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        FilmDAO filmDAO = daoObjectFactory.getFilmDAO();

        try {
            films = filmDAO.getFilms();
        } catch (DAOException e) {
            logger.error("DAO Exception", e);
            throw new ServiceException(e);
        }

        return films;
    }

    @Override
    public Film getFilmById(long id) throws ServiceException {
        Film film;

        if (id < 1) {
            logger.error("Incorrect film ID");
            throw new ServiceException("Incorrect film ID");
        }

        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        FilmDAO filmDAO = daoObjectFactory.getFilmDAO();

        try {
            film = filmDAO.getFilmById(id);
        } catch (DAOException e) {
            logger.error("DAO Exception", e);
            throw new ServiceException(e);
        }

        return film;
    }

    @Override
    public void addNewFilm(Film film) throws ServiceException {
        if (film.getDescription().isEmpty()) {
            logger.error("Film description is empty " + film.getDescription());
            throw new ServiceException("Film description is empty");
        }

        if (film.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            logger.error("Film price is negative " + film.getPrice());
            throw new ServiceException("Film price is negative");
        }

        if (film.getDiscount() < 0 || film.getDiscount() > 100) {
            logger.error("Film discount is invalid " + film.getDiscount());
            throw new ServiceException("Film discount is invalid");
        }

        if (film.getAuthor().isEmpty()) {
            logger.error("Film author is empty " + film.getAuthor());
            throw new ServiceException("Film author is empty");
        }

        if (film.getName().isEmpty()) {
            logger.error("Film name is empty " + film.getName());
            throw new ServiceException("Film name is empty");
        }

        if (film.getAgeRestriction() == AgeRestriction.EMPTY || film.getAgeRestriction() == null) {
            logger.error("Film age restriction is invalid " + film.getAgeRestriction().toString());
            throw new ServiceException("Film age restriction is invalid");
        }

        if (film.getMedia().getFilmPath().isEmpty()) {
            logger.error("Film path is empty " + film.getMedia().getFilmPath());
            throw new ServiceException("Film path is empty");
        }

        if (film.getMedia().getTrailerPath().isEmpty()) {
            logger.error("Film trailer path is empty " + film.getMedia().getTrailerPath());
            throw new ServiceException("Film trailer path is empty");
        }

        if (film.getCategories() == null || film.getCategories().isEmpty()) {
            logger.error("Film categories are empty");
            throw new ServiceException("Film categories are empty");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        FilmDAO filmDAO = daoFactory.getFilmDAO();

        try {
            filmDAO.addFilm(film);
        } catch (DAOException e) {
            logger.error("DAO Exception", e);
            throw new ServiceException("DAO Exception", e);
        }
    }

    @Override
    public void editFilm(Film film) throws ServiceException {
        if (film.getId() < 1) {
            logger.error("Film description is empty");
            throw new ServiceException("");
        }

        if (film.getDescription().isEmpty()) {
            logger.error("Film description is empty " + film.getDescription());
            throw new ServiceException("Film description is empty");
        }

        if (film.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            logger.error("Film price is negative " + film.getPrice());
            throw new ServiceException("Film price is negative");
        }

        if (film.getDiscount() < 0 || film.getDiscount() > 100) {
            logger.error("Film discount is invalid " + film.getDiscount());
            throw new ServiceException("Film discount is invalid");
        }

        if (film.getAuthor().isEmpty()) {
            logger.error("Film author is empty " + film.getAuthor());
            throw new ServiceException("Film author is empty");
        }

        if (film.getName().isEmpty()) {
            logger.error("Film name is empty " + film.getName());
            throw new ServiceException("Film name is empty");
        }

        if (film.getAgeRestriction() == AgeRestriction.EMPTY || film.getAgeRestriction() == null) {
            logger.error("Film age restriction is invalid " + film.getAgeRestriction().toString());
            throw new ServiceException("Film age restriction is invalid");
        }

        if (film.getCategories() == null || film.getCategories().isEmpty()) {
            logger.error("Film categories are empty");
            throw new ServiceException("Film categories are empty");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        FilmDAO filmDAO = daoFactory.getFilmDAO();

        try {
            filmDAO.editFilm(film);
        } catch (DAOException e) {
            logger.error("DAO Exception", e);
            throw new ServiceException("DAO Exception ", e);
        }
    }
}
