package by.bsuir.mycoolsite.dao;

import by.bsuir.mycoolsite.dao.exception.DAOException;

public interface LibraryDAO {
    void addFilm(long userId, long filmId) throws DAOException;
}
