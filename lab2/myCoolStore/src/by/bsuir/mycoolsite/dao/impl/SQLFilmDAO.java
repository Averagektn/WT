package by.bsuir.mycoolsite.dao.impl;

import by.bsuir.mycoolsite.bean.Category;
import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.bean.Media;
import by.bsuir.mycoolsite.bean.enums.AgeRestriction;
import by.bsuir.mycoolsite.connection.DBConnection;
import by.bsuir.mycoolsite.dao.FilmDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLFilmDAO implements FilmDAO {
    private static final Logger logger = LogManager.getLogger(SQLFilmDAO.class);
    private static final String QUERY_EDIT_FILM =
            "UPDATE film SET flm_description = ?, flm_price = ?, flm_discount = ?, flm_author = ?, flm_age = ?, " +
                    "flm_name = ? WHERE flm_id = ?";
    private static final String QUERY_CLEAR_FILM_CATEGORIES =
            "DELETE FROM m2m_film_category WHERE fc_film = ?";
    private static final String QUERY_ADD_FILM_CATEGORY =
            "INSERT INTO m2m_film_category (fc_film, fc_category) VALUES (?, ?)";
    private static final String QUERY_ADD_FILM =
            "INSERT INTO film (flm_description, flm_price, flm_discount, flm_author, flm_age, flm_name)" +
                    "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String QUERY_ADD_MEDIA =
            "INSERT INTO film_media (fm_id, fm_film_path, fm_trailer_path) VALUES (?, ?, ?)";
    private static final String QUERY_GET_FILM_BY_ID =
            "SELECT flm_description, flm_price, flm_discount, flm_author, flm_age, flm_name " +
                    "FROM film " +
                    "WHERE flm_id = ?";

    private static final String QUERY_GET_MEDIA_BY_FILM_ID =
            "SELECT fm_film_path, fm_trailer_path " +
                    "FROM film_media " +
                    "WHERE fm_id = ?";

    private static final String QUERY_GET_CATEGORIES_BY_FILM_ID =
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
                    "LEFT JOIN film_media ON flm_id = fm_id " +
                    "LEFT JOIN m2m_film_category ON flm_id = fc_film " +
                    "LEFT JOIN mycoolstore.category ON cat_id = fc_category";

    @Override
    public List<Film> getFilms() throws DAOException {
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Film> films = new ArrayList<>();

        DBConnection dbConnection = DBConnection.getInstance();

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
            logger.error("Query " + QUERY_GET_FILMS + " failed", e);
            throw new DAOException("Query " + QUERY_GET_FILMS + " failed", e);
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
                while (rsCategories.next()) {
                    categories.add(new Category(rsCategories.getString(1)));
                }

                film = new Film(id, description, price, media, discount, author, age, name, categories);
            } else {
                logger.error("Getting film by id failed");
                throw new DAOException("Getting film by id failed");
            }
        } catch (SQLException e) {
            logger.error("Getting film by id failed", e);
            throw new DAOException("Getting film by id failed", e);
        } finally {
            dbConnection.close(ps, rsFilm);
            dbConnection.close(ps, rsMedia);
            dbConnection.close(ps, rsCategories);
        }

        return film;
    }

    @Override
    public void addFilm(Film film) throws DAOException {
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBConnection dbConnection = DBConnection.getInstance();

        String description = film.getDescription();
        BigDecimal price = film.getPrice();
        int discount = film.getDiscount();
        String author = film.getAuthor();
        String ageRestriction = film.getAgeRestriction().toString();
        String name = film.getName();

        String trailer = film.getMedia().getTrailerPath();
        String filmFile = film.getMedia().getFilmPath();

        List<Category> categories = film.getCategories();

        try {
            con = dbConnection.getConnection();

            ps = con.prepareStatement(QUERY_ADD_FILM, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, description);
            ps.setBigDecimal(2, price);
            ps.setInt(3, discount);
            ps.setString(4, author);
            ps.setString(5, ageRestriction);
            ps.setString(6, name);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    long filmId = rs.getLong(1);

                    for (Category cat : categories) {
                        ps = con.prepareStatement(QUERY_ADD_FILM_CATEGORY);

                        ps.setLong(1, filmId);
                        ps.setLong(2, cat.getId());

                        rowsAffected = ps.executeUpdate();
                        if (rowsAffected < 0) {
                            logger.error("Query " + QUERY_ADD_FILM_CATEGORY + " failed");
                            throw new DAOException("Query " + QUERY_ADD_FILM_CATEGORY + " failed");
                        }
                    }

                    ps = con.prepareStatement(QUERY_ADD_MEDIA);
                    ps.setLong(1, filmId);
                    ps.setString(2, filmFile);
                    ps.setString(3, trailer);

                    rowsAffected = ps.executeUpdate();

                    if (rowsAffected < 0) {
                        logger.error("Query " + QUERY_ADD_MEDIA + " failed");
                        throw new DAOException("Query " + QUERY_ADD_MEDIA + " failed");
                    }
                } else {
                    logger.error("ID not found");
                    throw new DAOException("ID not found");
                }
            } else {
                logger.error("Query " + QUERY_ADD_FILM + " failed");
                throw new DAOException("Query " + QUERY_ADD_FILM + " failed");
            }

        } catch (SQLException e) {
            logger.error("Film adding query failed", e);
            throw new DAOException("Film adding query failed", e);
        } finally {
            dbConnection.close(ps, rs);
        }
    }

    @Override
    public void editFilm(Film film) throws DAOException {
        Connection con;
        PreparedStatement ps = null;
        DBConnection dbConnection = DBConnection.getInstance();

        String description = film.getDescription();
        BigDecimal price = film.getPrice();
        int discount = film.getDiscount();
        String author = film.getAuthor();
        String ageRestriction = film.getAgeRestriction().toString();
        String name = film.getName();
        long filmId = film.getId();
        List<Category> categories = film.getCategories();

        try {
            con = dbConnection.getConnection();

            ps = con.prepareStatement(QUERY_EDIT_FILM);
            ps.setString(1, description);
            ps.setBigDecimal(2, price);
            ps.setInt(3, discount);
            ps.setString(4, author);
            ps.setString(5, ageRestriction);
            ps.setString(6, name);
            ps.setLong(7, filmId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                ps = con.prepareStatement(QUERY_CLEAR_FILM_CATEGORIES);

                ps.setLong(1, filmId);

                ps.executeUpdate();

                for (Category cat : categories) {
                    ps = con.prepareStatement(QUERY_ADD_FILM_CATEGORY);

                    ps.setLong(1, filmId);
                    ps.setLong(2, cat.getId());

                    rowsAffected = ps.executeUpdate();
                    if (rowsAffected < 0) {
                        logger.error("Query " + QUERY_ADD_FILM_CATEGORY + " failed");
                        throw new DAOException("Query " + QUERY_ADD_FILM_CATEGORY + " failed");
                    }
                }
            } else {
                System.out.println("Query " + QUERY_EDIT_FILM + " failed");
                throw new DAOException("Query " + QUERY_EDIT_FILM + " failed");
            }

        } catch (SQLException e) {
            logger.error("Film editing query failed", e);
            throw new DAOException("Film editing query failed", e);
        } finally {
            dbConnection.close(ps, null);
        }
    }
}
