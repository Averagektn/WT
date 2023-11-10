package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.service.exception.ServiceException;

public interface LibraryService {
    void addFilm(long userId, long filmId) throws ServiceException;
}
