package by.bsuir.mycoolsite.dao;

import by.bsuir.mycoolsite.dao.exception.DAOException;

public interface CartDAO {
    void addFilm(long filmId, long userId) throws DAOException;
}
