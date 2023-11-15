package by.bsuir.mycoolsite.dao;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.util.List;

public interface CartDAO {
    void addFilm(long filmId, long userId) throws DAOException;

    List<Film> getCart(long userId) throws DAOException;

    void remove(long filmId, long userId) throws DAOException;

    void clear(long userId) throws DAOException;

    boolean contains(long userId, long filmId) throws DAOException;
}
