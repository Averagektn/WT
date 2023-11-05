package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.service.exception.ServiceException;

public interface FilmService {
    void addNewFilm(Film film) throws ServiceException;

    void addEditedFilm(Film film) throws ServiceException;
}
