package by.bsuir.mycoolsite.dao;

import by.bsuir.mycoolsite.bean.Media;
import by.bsuir.mycoolsite.dao.exception.DAOException;

public interface MediaDAO {
    void addMedia(Media media) throws DAOException;
}
