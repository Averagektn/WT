package by.bsuir.mycoolsite.dao.impl;

import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.bean.Media;
import by.bsuir.mycoolsite.bean.enums.AgeRestriction;
import by.bsuir.mycoolsite.connection.DBConnection;
import by.bsuir.mycoolsite.dao.FilmDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SQLFilmDAO implements FilmDAO {

    private static final String QUERY_GET_FILMS = "SELECT flm_id, flm_description, flm_price, flm_media, " +
            "flm_discount, flm_author, flm_age, flm_name FROM film;";

    @Override
    public List<Film> getFilms() throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Film> films = null;
        DBConnection dbConnection = DBConnection.getInstance();

        try {
            con = dbConnection.getConnection();

            ps = con.prepareStatement(QUERY_GET_FILMS);
            rs = ps.executeQuery();

            long id = -1;
            String description = "";
            BigDecimal price = BigDecimal.ZERO;
            Media media = null;
            int discount = 0;
            String author = "";
            AgeRestriction age = null;
            String name = "";

            while(rs.next()) {

            }
        } catch (SQLException e) {
            //LOG
            System.out.println("SQL Exception " + e.toString());
            throw new DAOException("Sql error");
        } finally {
            dbConnection.closeConnection(ps, rs);
        }

        return films;
    }

    @Override
    public void addFilm(Film film) throws DAOException {

    }

    @Override
    public void deleteFilm(long idFilm) throws DAOException {

    }

    @Override
    public void delete(Film film) throws DAOException {

    }
}
