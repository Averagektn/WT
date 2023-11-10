package by.bsuir.mycoolsite.dao.impl;

import by.bsuir.mycoolsite.bean.Feedback;
import by.bsuir.mycoolsite.bean.Film;
import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.bean.enums.Role;
import by.bsuir.mycoolsite.connection.DBConnection;
import by.bsuir.mycoolsite.dao.FeedbackDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLFeedbackDAO implements FeedbackDAO {
    private static final String QUERY_ADD_FEEDBACK =
            "INSERT INTO feedback (fbk_author, fbk_film, fbk_text, fbk_rating) VALUES (?,?,?,?)";
    private static final String QUERY_GET_FEEDBACK_BY_FILM_ID =
            "SELECT fbk_id, fbk_rating, fbk_text, usr_id, usr_email " +
                    "FROM feedback " +
                    "LEFT JOIN user ON fbk_author = usr_id " +
                    "WHERE fbk_film = ?";
    @Override
    public List<Feedback> getFilmFeedbacks(long filmId) throws DAOException {
        List<Feedback> feedbacks = new ArrayList<>();

        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBConnection dbConnection = DBConnection.getInstance();

        System.out.println("Connected");

        try {
            con = dbConnection.getConnection();

            ps = con.prepareStatement(QUERY_GET_FEEDBACK_BY_FILM_ID);
            ps.setLong(1, filmId);
            rs = ps.executeQuery();

            while (rs.next()) {
                long feedbackId = rs.getLong(1);
                int rating = rs.getInt(2);
                String text = rs.getString(3);
                long userId = rs.getLong(4);
                String userEmail = rs.getString(5);

                User user = new User(userId, userEmail, "", Role.CUSTOMER, 0);
                Feedback feedback = new Feedback(feedbackId, user, new Film(filmId), text, rating);

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
}
