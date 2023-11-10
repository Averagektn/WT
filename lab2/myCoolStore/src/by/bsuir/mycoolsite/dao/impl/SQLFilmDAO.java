package by.bsuir.mycoolsite.dao.impl;

import by.bsuir.mycoolsite.bean.*;
import by.bsuir.mycoolsite.bean.enums.AgeRestriction;
import by.bsuir.mycoolsite.bean.enums.Role;
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
    private static final String QUERY_ADD_FEEDBACK =
            "INSERT INTO feedback (fbk_author, fbk_film, fbk_text, fbk_rating) VALUES (?,?,?,?)";
    private static final String QUERY_GET_FILM_BY_ID =
            "SELECT flm_description, flm_price, flm_discount, flm_author, flm_age, flm_name " +
                    "FROM film " +
                    "WHERE flm_id = ?";

    private static final String QUERY_GET_MEDIA_BY_FILM_ID =
            "SELECT fm_film_path, fm_trailer_path " +
                    "FROM film_media " +
                    "WHERE fm_id = ?";

    private static final String QUERY_GET_FEEDBACK_BY_FILM_ID =
            "SELECT fbk_id, fbk_rating, fbk_text, usr_id, usr_email " +
                    "FROM feedback " +
                    "LEFT JOIN user ON fbk_author = usr_id " +
                    "WHERE fbk_film = ?";

    private static final String QUERY_GET_CATEGORIES_BY_FILM_ID=
            "SELECT cat_name " +
                    "FROM category " +
                    "LEFT JOIN m2m_film_category ON cat_id = fc_category " +
                    "WHERE fc_film = ?";
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
            System.out.println("SQL Exception " + e);
            throw new DAOException("Sql error");
        } finally {
            dbConnection.close(ps, rs);
        }

        return films;
    }

    @Override
    public Film getFilmById(long id) throws DAOException {
        Film film;

        Connection con;
        PreparedStatement ps = null;
        ResultSet rsFilm = null, rsMedia = null, rsCategories = null;
        DBConnection dbConnection = DBConnection.getInstance();

        System.out.println("Connected");

        try {
            con = dbConnection.getConnection();

            ps = con.prepareStatement(QUERY_GET_FILM_BY_ID);
            ps.setLong(1, id);
            rsFilm = ps.executeQuery();

            ps = con.prepareStatement(QUERY_GET_MEDIA_BY_FILM_ID);
            ps.setLong(1, id);
            rsMedia = ps.executeQuery();

            ps = con.prepareStatement(QUERY_GET_CATEGORIES_BY_FILM_ID);
            ps.setLong(1, id);
            rsCategories = ps.executeQuery();

            if (rsFilm.next() && rsMedia.next()) {
                String description = rsFilm.getString(1);
                BigDecimal price = rsFilm.getBigDecimal(2);
                int discount = rsFilm.getInt(3);
                String author = rsFilm.getString(4);
                AgeRestriction age = AgeRestriction.getAgeRestrictionFromString(rsFilm.getString(5));
                String name = rsFilm.getString(6);

                String filmPath = rsMedia.getString(1);
                String trailerPath = rsMedia.getString(2);
                Media media = new Media(trailerPath, filmPath);

                List<Category> categories = new ArrayList<>();
                while(rsCategories.next()) {
                    categories.add(new Category(rsCategories.getString(1)));
                }

                film = new Film(id, description, price, media, discount, author, age, name, categories);
            } else {
                //LOG
                System.out.println("Film info not found");
                throw new DAOException("Film info not found");
            }
        } catch (SQLException e) {
            //LOG
            System.out.println("SQL Exception " + e);
            throw new DAOException("Sql error");
        } finally {
            dbConnection.close(ps, rsFilm);
            dbConnection.close(ps, rsMedia);
            dbConnection.close(ps, rsCategories);
        }

        return film;
    }

    @Override
    public List<Feedback> getFilmFeedbacks(long id) throws DAOException {
        List<Feedback> feedbacks = new ArrayList<>();

        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBConnection dbConnection = DBConnection.getInstance();

        System.out.println("Connected");

        try {
            con = dbConnection.getConnection();

            ps = con.prepareStatement(QUERY_GET_FEEDBACK_BY_FILM_ID);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                long feedbackId = rs.getLong(1);
                int rating = rs.getInt(2);
                String text = rs.getString(3);
                long userId = rs.getLong(4);
                String userEmail = rs.getString(5);

                User user = new User(userId, userEmail, "", Role.CUSTOMER, 0);
                Feedback feedback = new Feedback(feedbackId, user, new Film(id), text, rating);

                feedbacks.add(feedback);
            }

        } catch (SQLException e) {
            //LOG
            System.out.println("SQL Exception " + e);
            throw new DAOException("Sql error");
        } finally {
            dbConnection.close(ps, rs);
        }

        return feedbacks;
    }

    @Override
    public void addFeedback(Feedback feedback) throws DAOException {
        Connection con;
        PreparedStatement ps = null;
        DBConnection dbConnection = DBConnection.getInstance();

        long author = feedback.getAuthor().getId();
        long film = feedback.getFilm().getId();
        String text = feedback.getText();
        int rating = feedback.getRating();

        try{
            con = dbConnection.getConnection();

            ps = con.prepareStatement(QUERY_ADD_FEEDBACK);
            ps.setLong(1, author);
            ps.setLong(2, film);
            ps.setString(3, text);
            ps.setInt(4, rating);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                //LOG
                System.out.println("Query failed");
                throw new DAOException("Query " + QUERY_ADD_FEEDBACK + " failed");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConnection.close(ps, null);
        }
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
