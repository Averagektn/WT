package by.bsuir.mycoolsite.dao;

import by.bsuir.mycoolsite.bean.Feedback;
import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.util.List;

public interface FilmDAO {
    List<Film> getFilms() throws DAOException;

    Film getFilmById(long id) throws DAOException;
    List<Feedback> getFilmFeedbacks(long id) throws DAOException;
    void addFeedback(Feedback feedback) throws DAOException;

    void addFilm(Film film) throws DAOException;

    void deleteFilm(long idFilm) throws DAOException;

    void delete(Film film) throws DAOException;

}
