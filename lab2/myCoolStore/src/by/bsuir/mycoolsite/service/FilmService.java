package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.bean.Feedback;
import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

public interface FilmService {
    List<Film> getFilms() throws ServiceException;

    Film getFilmById(long id) throws ServiceException;
    void addFeedback(Feedback feedback) throws ServiceException;

    List<Feedback> getFilmFeedbacks(long filmId) throws ServiceException;

    void addNewFilm(Film film) throws ServiceException;

    void addEditedFilm(Film film) throws ServiceException;
}
