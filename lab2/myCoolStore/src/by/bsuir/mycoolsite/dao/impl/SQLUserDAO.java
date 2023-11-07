package by.bsuir.mycoolsite.dao.impl;

import by.bsuir.mycoolsite.bean.User;
import by.bsuir.mycoolsite.bean.enums.Role;
import by.bsuir.mycoolsite.connection.DBConnection;
import by.bsuir.mycoolsite.dao.UserDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;

import java.sql.*;

public class SQLUserDAO implements UserDAO {
    private static final String QUERY_REGISTER =
            "INSERT INTO user (usr_email, usr_password, usr_role, usr_banned_by) VALUES (?,?,?,?)";
    private static final String QUERY_AUTHORIZATION =
            "SELECT usr_id, usr_role, usr_banned_by FROM user WHERE usr_email = ? AND usr_password = ?";

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
                long bannedBy = rs.getLong(3);
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
    public long registration(User user) throws DAOException {
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs;
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
            dbConnection.close(ps, null);
        }

        return id;
    }
}
