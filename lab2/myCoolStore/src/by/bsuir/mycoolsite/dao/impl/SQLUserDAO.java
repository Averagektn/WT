package by.bsuir.mycoolsite.dao.impl;

import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.config.Config;
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
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // LOG
        System.out.println("Authorization of " + email);
        User user = new User(email, password);

        try {
            Class.forName(Config.DBConnectionClassname);
            con = DriverManager.getConnection(Config.DBConnectionURL, Config.DBUser, Config.DBPassword);

            ps = con.prepareStatement(QUERY_AUTHORIZATION);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getEmail());

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
        } catch (ClassNotFoundException e) {
            //LOG
            System.out.println("ClassNotFound exception in SQLUserDAO");
            throw new DAOException("Class not found");
        } catch (SQLException e) {
            //LOG
            System.out.println("SQL Exception in SQLUserDAO " + e.toString());
            throw new DAOException("Sql error");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                //LOG
                System.out.println("Connection closing exception in SQLUserDAO");
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void registration(User user) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // LOG
        System.out.println("Registration of " + user.getEmail());

        try {
            Class.forName(Config.DBConnectionClassname);
            con = DriverManager.getConnection(Config.DBConnectionURL, Config.DBUser, Config.DBPassword);

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
        } catch (ClassNotFoundException e) {
            //LOG
            System.out.println("ClassNotFound exception in SQLUserDAO");
            throw new DAOException("Class not found");
        } catch (SQLException e) {
            //LOG
            System.out.println("SQL Exception in SQLUserDAO " + e.toString());
            throw new DAOException("Sql error");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                //LOG
                System.out.println("Connection closing exception in SQLUserDAO");
                throw new RuntimeException(e);
            }
        }
    }
}
