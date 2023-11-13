package by.bsuir.mycoolsite.dao.impl;

import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.bean.enums.Role;
import by.bsuir.mycoolsite.connection.DBConnection;
import by.bsuir.mycoolsite.dao.UserDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLUserDAO implements UserDAO {
    private static final String QUERY_UNBAN =
            "UPDATE user SET usr_banned_by = NULL WHERE usr_id = ?";
    private static final String QUERY_GET_BANNED_USERS =
            "SELECT usr_id, usr_email, usr_banned_by FROM user WHERE usr_banned_by IS NOT NULL";
    private static final String QUERY_BAN =
            "UPDATE user SET usr_banned_by = ? WHERE usr_id = ?";
    private static final String QUERY_REGISTER =
            "INSERT INTO user (usr_email, usr_password, usr_role, usr_banned_by) VALUES (?,?,?,?)";
    private static final String QUERY_AUTHORIZATION =
            "SELECT usr_id, usr_role, usr_banned_by FROM user WHERE usr_email = ? AND usr_password = ?";
    private static final String QUERY_USER_FILM =
            "SELECT * FROM user_film WHERE uf_user = ? AND uf_film = ?";
    private static final String QUERY_USER_BANNED =
            "SELECT usr_banned_by FROM user WHERE usr_id = ?";

    @Override
    public User signIn(String email, String password) throws DAOException {
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBConnection dbConnection = DBConnection.getInstance();

        // LOG
        System.out.println("Authorization of " + email);
        User user = new User(email, password);

        try {
            con = dbConnection.getConnection();

            ps = con.prepareStatement(QUERY_AUTHORIZATION);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());

            rs = ps.executeQuery();

            if (!rs.next()) {
                //LOG
                System.out.println("0 rows affected. Selection error");
                throw new DAOException("Authorization failed");
            } else {
                user.setId(rs.getLong(1));
                user.setRole(Role.valueOf(rs.getString(2).toUpperCase()));
                rs.getLong(3);

                if (!rs.wasNull()) {
                    //LOG
                    System.out.println("User is banned");
                    throw new DAOException("User is banned");
                }
            }
        } catch (SQLException e) {
            //LOG
            System.out.println("SQL Exception in SQLUserDAO " + e);
            throw new DAOException("Sql error");
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

        // LOG
        System.out.println("Checking film ownership");

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
            //LOG
            System.out.println("SQL Exception " + e);
            throw new DAOException("Sql error");
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

        System.out.println("Checking if user is banned");

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
            //LOG
            System.out.println("SQL Exception " + e);
            throw new DAOException("Sql error in isBanned");
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

        // LOG
        System.out.println("Registration of " + user.getEmail());

        try {
            con = dbConnection.getConnection();

            ps = con.prepareStatement(QUERY_REGISTER, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole().toString());
            ps.setNull(4, Types.NULL);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected <= 0) {
                //LOG
                System.out.println("0 rows affected. Insertion error");
                throw new DAOException("Registration failed");
            }

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            } else {
                System.out.println("0 rows affected. Key getting error");
                throw new DAOException("Registration failed");
            }

        } catch (SQLException e) {
            //LOG
            System.out.println("SQL Exception in SQLUserDAO " + e);
            throw new DAOException("Sql error");
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
                //LOG
                System.out.println("0 rows affected. Update error");
                throw new DAOException("Ban failed");
            }
        } catch (SQLException e) {
            //LOG
            System.out.println("SQL Exception in SQLUserDAO " + e);
            throw new DAOException("Sql error");
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
                //LOG
                System.out.println("0 rows affected. Update error");
                throw new DAOException("Unban failed");
            }
        } catch (SQLException e) {
            //LOG
            System.out.println("SQL Exception in SQLUserDAO " + e);
            throw new DAOException("Sql error");
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
            while (rs.next()){
                long id = rs.getLong(1);
                String email = rs.getString(2);
                long bannedBy = rs.getLong(3);

                User user = new User(id, email, "", Role.CUSTOMER, bannedBy);

                users.add(user);
            }
        } catch (SQLException e) {
            //LOG
            System.out.println("SQL Exception in SQLUserDAO " + e);
            throw new DAOException("Sql error");
        } finally {
            dbConnection.close(ps, rs);
        }

        return users;
    }
}
