package by.bsuir.mycoolsite.dao.impl;

import by.bsuir.mycoolsite.bean.Media;
import by.bsuir.mycoolsite.connection.DBConnection;
import by.bsuir.mycoolsite.dao.MediaDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLMediaDAO implements MediaDAO {
    private static final String QUERY_ADD_MEDIA =
            "INSERT INTO film_media (fm_film_path, fm_trailer_path) VALUES (?, ?)";
    @Override
    public void addMedia(Media media) throws DAOException {
        DBConnection dbConnection = DBConnection.getInstance();
        Connection con = dbConnection.getConnection();
        PreparedStatement ps = null;

        try{
            ps = con.prepareStatement(QUERY_ADD_MEDIA);

            ps.setString(1, media.getFilmPath());
            ps.setString(2, media.getTrailerPath());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0){
                throw new DAOException("Media adding failed");
            }
        } catch (SQLException e) {
            //LOG
            throw new DAOException("Media adding failed");
        } finally {
            dbConnection.close(ps, null);
        }
    }
}
