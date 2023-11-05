package by.bsuir.mycoolsite.dao;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.dao.exception.DAOException;

public interface FilmDAO {
    void addBook(Film film) throws DAOException;

    void deleteFilm(long idFilm) throws DAOException;

    void delete(Film film) throws DAOException;

}
