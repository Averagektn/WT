package by.bsuir.mycoolsite.service.impl;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.service.FilmService;
import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

public class FilmServiceImpl implements FilmService {
    @Override
    public List<Film> getFilms() throws ServiceException {
        return null;
    }

    @Override
    public void addNewFilm(Film film) throws ServiceException {

    }

    @Override
    public void addEditedFilm(Film film) throws ServiceException {

    }
}
