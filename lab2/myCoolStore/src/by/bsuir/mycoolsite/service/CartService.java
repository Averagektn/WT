package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.service.exception.ServiceException;

public interface CartService {
    public void addFilm(long filmId, long userId) throws ServiceException;
}
