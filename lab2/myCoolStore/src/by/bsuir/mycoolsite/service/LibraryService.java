package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

public interface LibraryService {
    void addFilm(long userId, long filmId) throws ServiceException;

    List<Film> getUserFilms(long userId) throws ServiceException;
}
