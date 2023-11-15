package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.service.exception.ServiceException;

import java.util.List;

public interface CartService {
    void addFilm(long filmId, long userId) throws ServiceException;

    List<Film> getCart(long userId) throws ServiceException;

    void remove(long filmId, long userId) throws ServiceException;

    void clear(long userId) throws ServiceException;

    boolean contains(long userId, long filmId) throws ServiceException;
}
