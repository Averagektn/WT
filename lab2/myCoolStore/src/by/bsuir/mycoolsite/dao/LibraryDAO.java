package by.bsuir.mycoolsite.dao;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.util.List;

public interface LibraryDAO {
    void addFilm(long userId, long filmId) throws DAOException;
    List<Film> getUserFilms(long userId) throws DAOException;
}
