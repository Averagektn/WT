package by.bsuir.mycoolsite.dao.impl;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.bean.Media;
import by.bsuir.mycoolsite.bean.enums.AgeRestriction;
import by.bsuir.mycoolsite.connection.DBConnection;
import by.bsuir.mycoolsite.dao.LibraryDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLLibraryDAO implements LibraryDAO {
    private static final String QUERY_GET_USER_FILMS =
            "SELECT flm_id, flm_description, flm_price, flm_discount, flm_author, flm_age, flm_name " +
                    "FROM film " +
                    "JOIN user_film on flm_id = uf_film " +
                    "WHERE uf_user = ?";
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

    @Override
    public List<Film> getUserFilms(long userId) throws DAOException {
        List<Film> films = new ArrayList<>();

        DBConnection dbConnection = DBConnection.getInstance();
        Connection con = dbConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            ps = con.prepareStatement(QUERY_GET_USER_FILMS);
            ps.setLong(1, userId);
            rs = ps.executeQuery();

            while(rs.next()){
                long filmId = rs.getLong(1);
                String description = rs.getString(2);
                BigDecimal price = rs.getBigDecimal(3);
                int discount = rs.getInt(4);
                String author = rs.getString(5);
                AgeRestriction age = AgeRestriction.getAgeRestrictionFromString(rs.getString(6));
                String name = rs.getString(7);

                Film film = new Film(filmId, description, price, new Media(filmId), discount, author, age, name,
                        new ArrayList<>());

                films.add(film);
            }
        } catch (SQLException e) {
            //LOG
            System.out.println("Library films receiving error DAO");
            throw new DAOException("Library films receiving error DAO");
        } finally {
            dbConnection.close(ps, rs);
        }

        return films;
    }
}
