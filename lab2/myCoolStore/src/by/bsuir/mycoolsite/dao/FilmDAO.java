package by.bsuir.mycoolsite.dao;

import by.bsuir.mycoolsite.bean.Film;

public interface FilmDAO {
    void addBook(Film film);
    void deleteFilm(long idFilm);
    void delete(Film film);

}
