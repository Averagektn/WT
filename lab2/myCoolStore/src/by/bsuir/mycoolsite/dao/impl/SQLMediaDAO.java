package by.bsuir.mycoolsite.dao.impl;

import by.bsuir.mycoolsite.bean.Media;
import by.bsuir.mycoolsite.connection.DBConnection;
import by.bsuir.mycoolsite.dao.MediaDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.sql.*;

public class SQLMediaDAO implements MediaDAO {
    private static final String QUERY_ADD_MEDIA =
            "INSERT INTO film_media (fm_film_path, fm_trailer_path) VALUES (?, ?)";
    @Override
    public long addMedia(Media media) throws DAOException {
        long id;

        DBConnection dbConnection = DBConnection.getInstance();
        Connection con = dbConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(QUERY_ADD_MEDIA, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, media.getFilmPath());
            ps.setString(2, media.getTrailerPath());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0){
                throw new DAOException("Media adding failed");
            }

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            } else {
                System.out.println("0 rows affected. Key getting error");
                throw new DAOException("Registration failed");
            }
        } catch (SQLException e) {
            //LOG
            throw new DAOException("Media adding failed");
        } finally {
            dbConnection.close(ps, rs);
        }

        return id;
    }
}
