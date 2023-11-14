package by.bsuir.mycoolsite.service.impl;

import by.bsuir.mycoolsite.bean.Media;
import by.bsuir.mycoolsite.dao.MediaDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import by.bsuir.mycoolsite.dao.factory.DAOFactory;
import by.bsuir.mycoolsite.service.MediaService;
import by.bsuir.mycoolsite.service.exception.ServiceException;

public class MediaServiceImpl implements MediaService {
    @Override
    public void addMedia(Media media) throws ServiceException {
        if (media.getFilmPath().isEmpty()) {
            throw new ServiceException("media filmpath is empty");
        }
        if (media.getTrailerPath().isEmpty()) {
            throw new ServiceException("media trailerpath is empty");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        MediaDAO mediaDAO = daoFactory.getMediaDAO();

        try {
            mediaDAO.addMedia(media);
        } catch (DAOException e) {
            throw new ServiceException("DAO: " + e);
        }
    }
}
