package by.bsuir.mycoolsite.service;

import by.bsuir.mycoolsite.bean.Media;
import by.bsuir.mycoolsite.service.exception.ServiceException;

public interface MediaService {
    void addMedia(Media media) throws ServiceException;
}
