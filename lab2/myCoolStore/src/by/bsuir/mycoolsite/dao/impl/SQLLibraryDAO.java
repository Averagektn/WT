package by.bsuir.mycoolsite.dao.impl;

import by.bsuir.mycoolsite.connection.DBConnection;
import by.bsuir.mycoolsite.dao.LibraryDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLLibraryDAO implements LibraryDAO {
    private static final String QUERY_ADD_FILM =
            "INSERT INTO user_film (uf_user, uf_film) VALUES (?, ?)";
    @Override
    public void addFilm(long userId, long filmId) throws DAOException {
        DBConnection dbConnection = DBConnection.getInstance();
        Connection con = dbConnection.getConnection();
        PreparedStatement ps = null;

        try{
            ps = con.prepareStatement(QUERY_ADD_FILM);
            ps.setLong(1, userId);
            ps.setLong(2, filmId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0){
                //LOG
                System.out.println("Film adding to library error");
                throw new DAOException("Film adding to library error");
            }
        } catch (SQLException e) {
            //LOG
            System.out.println("Film adding to library error");
            throw new DAOException("Film adding to library error");
        } finally {
            dbConnection.close(ps, null);
        }
    }
}
