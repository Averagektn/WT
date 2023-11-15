package by.bsuir.mycoolsite.dao.impl;

import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.bean.enums.Role;
import by.bsuir.mycoolsite.connection.DBConnection;
import by.bsuir.mycoolsite.dao.UserDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLUserDAO implements UserDAO {
    private static final Logger logger = LogManager.getLogger(SQLUserDAO.class);
    private static final String QUERY_UNBAN =
            "UPDATE user SET usr_banned_by = NULL " +
                    "WHERE usr_id = ?";
    private static final String QUERY_GET_BANNED_USERS =
            "SELECT usr_id, usr_email, usr_banned_by FROM user " +
                    "WHERE usr_banned_by IS NOT NULL";
    private static final String QUERY_BAN =
            "UPDATE user SET usr_banned_by = ? " +
                    "WHERE usr_id = ?";
    private static final String QUERY_REGISTER =
            "INSERT INTO user (usr_email, usr_password, usr_role, usr_banned_by) VALUES (?,?,?,?)";
    private static final String QUERY_AUTHORIZATION =
            "SELECT usr_id, usr_role, usr_banned_by " +
                    "FROM user " +
                    "WHERE usr_email = ? AND usr_password = ?";
    private static final String QUERY_USER_FILM =
            "SELECT * " +
                    "FROM user_film " +
                    "WHERE uf_user = ? AND uf_film = ?";
    private static final String QUERY_USER_BANNED =
            "SELECT usr_banned_by " +
                    "FROM user " +
                    "WHERE usr_id = ?";

    @Override
    public User signIn(String email, String password) throws DAOException {
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBConnection dbConnection = DBConnection.getInstance();

        logger.info("Authorization of " + email);

        User user = new User(email, password);

        try {
            con = dbConnection.getConnection();

            ps = con.prepareStatement(QUERY_AUTHORIZATION);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());

            rs = ps.executeQuery();

            if (!rs.next()) {
                logger.error("0 rows affected. Selection error in " + QUERY_AUTHORIZATION);
                throw new DAOException("0 rows affected. Selection error in " + QUERY_AUTHORIZATION);
            } else {
                user.setId(rs.getLong(1));
                user.setRole(Role.valueOf(rs.getString(2).toUpperCase()));
                rs.getLong(3);

                if (!rs.wasNull()) {
                    logger.error("User is banned");
                    throw new DAOException("User is banned");
                }
            }
        } catch (SQLException e) {
            logger.error("Query " + QUERY_AUTHORIZATION + " failed", e);
            throw new DAOException("Query " + QUERY_AUTHORIZATION + " failed", e);
        } finally {
            dbConnection.close(ps, rs);
        }

        return user;
    }

    @Override
    public boolean isFilmOwner(long userId, long filmId) throws DAOException {
        boolean isFilmOwner = false;

        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBConnection dbConnection = DBConnection.getInstance();

        logger.info("Checking film ownership");

        try {
            con = dbConnection.getConnection();

            ps = con.prepareStatement(QUERY_USER_FILM);
            ps.setLong(1, userId);
            ps.setLong(2, filmId);

            rs = ps.executeQuery();

            if (rs.next()) {
                isFilmOwner = true;
            }
        } catch (SQLException e) {
            logger.error("Query " + QUERY_USER_FILM + " failed", e);
            throw new DAOException("Query " + QUERY_USER_FILM + " failed", e);
        } finally {
            dbConnection.close(ps, rs);
        }

        return isFilmOwner;
    }

    @Override
    public boolean isBanned(long id) throws DAOException {
        boolean isBanned = false;

        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBConnection dbConnection = DBConnection.getInstance();

        logger.info("Checking if user is banned");

        try {
            con = dbConnection.getConnection();

            ps = con.prepareStatement(QUERY_USER_BANNED);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                rs.getLong(1);

                if (!rs.wasNull()) {
                    isBanned = true;
                }
            }
        } catch (SQLException e) {
            logger.error("Query " + QUERY_USER_BANNED + " failed", e);
            throw new DAOException("Query " + QUERY_USER_BANNED + " failed", e);
        } finally {
            dbConnection.close(ps, rs);
        }

        return isBanned;
    }

    @Override
    public long registration(User user) throws DAOException {
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBConnection dbConnection = DBConnection.getInstance();

        long id;

        logger.info("Registration of " + user.getEmail());

        try {
            con = dbConnection.getConnection();

            ps = con.prepareStatement(QUERY_REGISTER, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole().toString());
            ps.setNull(4, Types.NULL);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected <= 0) {
                logger.error("Query " + QUERY_REGISTER + " failed");
                throw new DAOException("Query " + QUERY_REGISTER + " failed");
            }

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            } else {
                logger.error("0 rows affected. Key getting error. Registration failed");
                throw new DAOException("0 rows affected. Key getting error. Registration failed");
            }
        } catch (SQLException e) {
            logger.error("Query " + QUERY_REGISTER + " failed", e);
            throw new DAOException("Query " + QUERY_REGISTER + " failed", e);
        } finally {
            dbConnection.close(ps, rs);
        }

        return id;
    }

    @Override
    public void ban(long userId, long adminId) throws DAOException {
        DBConnection dbConnection = DBConnection.getInstance();
        Connection con = dbConnection.getConnection();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(QUERY_BAN);

            ps.setLong(1, adminId);
            ps.setLong(2, userId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected <= 0) {
                logger.error("0 rows affected. Update error. Ban failed");
                throw new DAOException("0 rows affected. Update error. Ban failed");
            }
        } catch (SQLException e) {
            logger.error("Query " + QUERY_BAN + " failed", e);
            throw new DAOException("Query " + QUERY_BAN + " failed", e);
        } finally {
            dbConnection.close(ps, null);
        }
    }

    @Override
    public void unban(long userId) throws DAOException {
        DBConnection dbConnection = DBConnection.getInstance();
        Connection con = dbConnection.getConnection();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(QUERY_UNBAN);

            ps.setLong(1, userId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected <= 0) {
                logger.error("0 rows affected. Update error. Unban failed");
                throw new DAOException("0 rows affected. Update error. Unban failed");
            }
        } catch (SQLException e) {
            logger.error("Query " + QUERY_UNBAN + " failed");
            throw new DAOException("Query " + QUERY_UNBAN + " failed");
        } finally {
            dbConnection.close(ps, null);
        }
    }

    @Override
    public List<User> getBannedUsers() throws DAOException {
        List<User> users = new ArrayList<>();

        DBConnection dbConnection = DBConnection.getInstance();
        Connection con = dbConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(QUERY_GET_BANNED_USERS);

            rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong(1);
                String email = rs.getString(2);
                long bannedBy = rs.getLong(3);

                User user = new User(id, email, "", Role.CUSTOMER, bannedBy);

                users.add(user);
            }
        } catch (SQLException e) {
            logger.error("Query " + QUERY_GET_BANNED_USERS + " failed", e);
            throw new DAOException("Query " + QUERY_GET_BANNED_USERS + " failed", e);
        } finally {
            dbConnection.close(ps, rs);
        }

        return users;
    }
}
