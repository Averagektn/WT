package by.bsuir.mycoolsite.dao.impl;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.bean.Media;
import by.bsuir.mycoolsite.bean.enums.AgeRestriction;
import by.bsuir.mycoolsite.connection.DBConnection;
import by.bsuir.mycoolsite.dao.CartDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLCartDAO implements CartDAO {
    private static final String QUERY_CLEAR_CART =
            "DELETE FROM cart WHERE crt_user = ?";
    private static final String QUERY_REMOVE_FILM =
            "DELETE FROM cart WHERE crt_film = ? AND crt_user = ?";
    private static final String QUERY_ADD_FILM =
            "INSERT INTO cart (crt_film, crt_user) VALUES (?, ?)";
    private static final String QUERY_GET_CART_FILMS =
            "SELECT flm_id, flm_description, flm_price, flm_media, flm_discount, flm_author, flm_age, flm_name " +
                    "FROM film " +
                    "JOIN cart ON flm_id = crt_film " +
                    "WHERE crt_user = ?";
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

    @Override
    public List<Film> getCart(long userId) throws DAOException {
        List<Film> cartFilms = new ArrayList<>();

        DBConnection dbConnection = DBConnection.getInstance();
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            con = dbConnection.getConnection();

            ps = con.prepareStatement(QUERY_GET_CART_FILMS);
            ps.setLong(1, userId);
            rs = ps.executeQuery();

            while(rs.next()){
                long filmId = rs.getLong(1);
                String description = rs.getString(2);
                BigDecimal price = rs.getBigDecimal(3);
                Media media = new Media(rs.getLong(4));
                int discount = rs.getInt(5);
                String author = rs.getString(6);
                AgeRestriction age = AgeRestriction.getAgeRestrictionFromString(rs.getString(7));
                String name = rs.getString(8);

                Film film = new Film(filmId, description, price, media, discount, author, age, name, new ArrayList<>());

                cartFilms.add(film);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConnection.close(ps, rs);
        }

        return cartFilms;
    }

    @Override
    public void remove(long filmId, long userId) throws DAOException {
        DBConnection dbConnection = DBConnection.getInstance();
        Connection con;
        PreparedStatement ps = null;

        try{
            con = dbConnection.getConnection();

            ps = con.prepareStatement(QUERY_REMOVE_FILM);
            ps.setLong(1, filmId);
            ps.setLong(2, userId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0){
                //LOG
                System.out.println("Deleting from cart DAO error");
                throw new DAOException("Deleting from cart DAO error");
            }
        } catch (SQLException e) {
            //LOG
            System.out.println("Deleting from cart DAO error");
            throw new DAOException("Deleting from cart DAO error");
        }
    }

    @Override
    public void clear(long userId) throws DAOException {
        DBConnection dbConnection = DBConnection.getInstance();
        Connection con = dbConnection.getConnection();
        PreparedStatement ps = null;

        try{
            ps = con.prepareStatement(QUERY_CLEAR_CART);
            ps.setLong(1, userId);

            ps.executeUpdate();
        } catch (SQLException e) {
            //LOG
            System.out.println("Clear query DAO error");
            throw new DAOException("Clear query DAO error");
        } finally {
            dbConnection.close(ps, null);
        }
    }
}
