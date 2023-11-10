package by.bsuir.mycoolsite.dao.impl;

import by.bsuir.mycoolsite.connection.DBConnection;
import by.bsuir.mycoolsite.dao.CartDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLCartDAO implements CartDAO {
    private static final String QUERY_ADD_FILM =
            "INSERT INTO cart (crt_film, crt_user) VALUES (?, ?)";
    @Override
    public void addFilm(long filmId, long userId) throws DAOException {
        DBConnection dbConnection = DBConnection.getInstance();
        Connection con;
        PreparedStatement ps = null;

        try{
            con = dbConnection.getConnection();

            ps = con.prepareStatement(QUERY_ADD_FILM);

            ps.setLong(1, filmId);
            ps.setLong(2, userId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0){
                //LOG
                System.out.println("Adding to cart failed");
                throw new DAOException("Adding to cart failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConnection.close(ps, null);
        }
    }
}
