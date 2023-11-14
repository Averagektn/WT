package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

public interface FilmService {
    List<Film> getFilms() throws ServiceException;

    Film getFilmById(long id) throws ServiceException;

    void addNewFilm(Film film) throws ServiceException;

    void editFilm(Film film) throws ServiceException;
}
