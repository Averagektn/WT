package by.bsuir.mycoolsite.dao.impl;

import by.bsuir.mycoolsite.bean.Category;
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
import java.util.ArrayList;
import java.util.List;

public class SQLFilmDAO implements FilmDAO {

    private static final String QUERY_GET_FILMS =
            "SELECT " +
                "flm_id, " +
                "flm_description, " +
                "flm_price, " +
                "fm_trailer_path, " +
                "flm_discount, " +
                "flm_author, " +
                "flm_age, " +
                "flm_name, " +
                "cat_name " +
            "FROM film " +
                "LEFT JOIN film_media ON flm_media = fm_id " +
                "LEFT JOIN m2m_film_category ON flm_id = fc_film " +
                "LEFT JOIN mycoolstore.category ON cat_id = fc_category";

    @Override
    public List<Film> getFilms() throws DAOException {
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Film> films = new ArrayList<>();

        DBConnection dbConnection = DBConnection.getInstance();

        System.out.println("Connected");

        try {
            con = dbConnection.getConnection();

            ps = con.prepareStatement(QUERY_GET_FILMS);
            rs = ps.executeQuery();

            while (rs.next()) {
                long filmID = rs.getLong("flm_id");
                String filmDescription = rs.getString("flm_description");
                BigDecimal filmPrice = rs.getBigDecimal("flm_price");
                Media filmTrailer = new Media(rs.getString("fm_trailer_path"));
                int filmDiscount = rs.getInt("flm_discount");
                String filmAuthor = rs.getString("flm_author");
                AgeRestriction filmAge = AgeRestriction.getAgeRestrictionFromString(rs.getString("flm_age"));
                String filmName = rs.getString("flm_name");
                Category filmCategory = new Category(rs.getString("cat_name"));

                int lastElement = films.size() - 1;

                if (!films.isEmpty() && films.get(lastElement).getId() == filmID) {
                    films.get(lastElement).getCategories().add(filmCategory);
                } else {
                    films.add(new Film(
                            filmID,
                            filmDescription,
                            filmPrice,
                            filmTrailer,
                            filmDiscount,
                            filmAuthor,
                            filmAge,
                            filmName,
                            new ArrayList<>() {{
                                add(filmCategory);
                            }}
                    ));
                }
            }

        } catch (SQLException e) {
            //LOG
            System.out.println("SQL Exception " + e.toString());
            throw new DAOException("Sql error");
        } finally {
            dbConnection.close(ps, rs);
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
