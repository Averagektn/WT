package by.bsuir.mycoolsite.connection;

import by.bsuir.mycoolsite.config.Config;
import by.bsuir.mycoolsite.connection.exception.DBConnectionException;

import java.sql.*;

public class DBConnection {
    private static DBConnection instance;
    private final Connection connection;

    private DBConnection() throws DBConnectionException {
        try {
            Class.forName(Config.DBConnectionClassname);
            connection = DriverManager.getConnection(Config.DBConnectionURL, Config.DBUser, Config.DBPassword);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFound exception in DBConnection");
            throw new DBConnectionException("Class not found");
        } catch (SQLException e) {
            System.out.println("SQL Exception in DBConnection: " + e.toString());
            throw new DBConnectionException("SQL error");
        }
    }

    public static synchronized DBConnection getInstance() throws RuntimeException {
        if (instance == null) {
            try {
                instance = new DBConnection();
            } catch (DBConnectionException e) {
                //LOG
                throw new RuntimeException("Failed to create DBConnection instance", e);
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws RuntimeException {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            //LOG
            throw new RuntimeException("Connection closing exception", e);
        }
    }

    public void closeConnection(PreparedStatement ps, ResultSet rs) throws RuntimeException {
        try {
            closeConnection();
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            //LOG
            throw new RuntimeException("Connection closing exception", e);
        }
    }
}

