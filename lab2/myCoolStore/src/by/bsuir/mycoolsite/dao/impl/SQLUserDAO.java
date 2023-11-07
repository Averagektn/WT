package by.bsuir.mycoolsite.dao.impl;

import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.connection.DBConnection;
import by.bsuir.mycoolsite.dao.UserDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.sql.*;

public class SQLUserDAO implements UserDAO {
    private static final String QUERY_REGISTER =
            "INSERT INTO user (usr_email, usr_password, usr_role, usr_banned_by) VALUES (?,?,?,?)";
    private static final String QUERY_AUTHORIZATION =
            "SELECT usr_id, usr_banned_by FROM user WHERE usr_email = ? AND usr_password = ?";

    @Override
    public void signIn(String email, String password) throws DAOException {
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
                long usrBannedBy = rs.getLong(2);
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
    }

    @Override
    public void registration(User user) throws DAOException {
        Connection con;
        PreparedStatement ps = null;

        DBConnection dbConnection = DBConnection.getInstance();

        // LOG
        System.out.println("Registration of " + user.getEmail());

        try {
            con = dbConnection.getConnection();

            ps = con.prepareStatement(QUERY_REGISTER);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole().toString());

            if (user.isBanned()) {
                //LOG
                System.out.println("User is banned");
                ps.setLong(4, user.getBannedBy());
            } else {
                //LOG
                System.out.println("User is not banned");
                ps.setNull(4, Types.NULL);
            }

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected <= 0) {
                //LOG
                System.out.println("0 rows affected. Insertion error");
                throw new DAOException("Registration failed");
            }
        } catch (SQLException e) {
            //LOG
            System.out.println("SQL Exception in SQLUserDAO " + e);
            throw new DAOException("Sql error");
        } finally {
            dbConnection.close(ps, null);
        }
    }
}
