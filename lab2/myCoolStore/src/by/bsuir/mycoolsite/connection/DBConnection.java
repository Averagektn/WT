package by.bsuir.mycoolsite.connection;

import by.bsuir.mycoolsite.broker.RabbitMQ;
import by.bsuir.mycoolsite.config.Config;
import by.bsuir.mycoolsite.connection.exception.DBConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

/**
 * Singleton class for managing a database connection.
 */
public class DBConnection {
    private static final Logger logger = LogManager.getLogger(DBConnection.class);
    private static DBConnection instance;
    private final Connection connection;

    /**
     * Private constructor to create a database connection.
     *
     * @throws DBConnectionException If there is an issue with the database connection.
     */
    private DBConnection() throws DBConnectionException {
        try {
            Class.forName(Config.DBConnectionClassname);
            connection = DriverManager.getConnection(Config.DBConnectionURL, Config.DBUser, Config.DBPassword);
        } catch (ClassNotFoundException e) {
            RabbitMQ.sendMessage("ClassNotFound exception in DBConnection: class name: " + Config.DBConnectionClassname);
            logger.error("ClassNotFound exception in DBConnection: class name: " + Config.DBConnectionClassname, e);
            throw new DBConnectionException("Class not found");
        } catch (SQLException e) {
            RabbitMQ.sendMessage("SQL Exception in DBConnection");
            logger.error("SQL Exception in DBConnection", e);
            throw new DBConnectionException("SQL error");
        }
    }

    /**
     * Returns the singleton instance of the DBConnection.
     *
     * @return The DBConnection instance.
     * @throws RuntimeException If there is an issue creating the DBConnection instance.
     */
    public static synchronized DBConnection getInstance() throws RuntimeException {
        if (instance == null) {
            try {
                instance = new DBConnection();
            } catch (DBConnectionException e) {
                RabbitMQ.sendMessage("DBConnectionException while creating new connection");
                logger.error("DBConnectionException while creating new connection", e);
                throw new RuntimeException("Failed to create DBConnection instance", e);
            }
        }
        return instance;
    }

    /**
     * Gets the database connection.
     *
     * @return The database connection.
     */
    public Connection getConnection() {
        RabbitMQ.sendMessage("Connection provided");
        logger.error("Connection provided");
        return connection;
    }

    /**
     * Closes the database connection.
     *
     * @throws RuntimeException If there is an issue closing the connection.
     */
    public void closeConnection() throws RuntimeException {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            RabbitMQ.sendMessage("Connection closing exception");
            logger.error("Connection closing exception", e);
            throw new RuntimeException("Connection closing exception", e);
        }
    }

    /**
     * Closes the provided PreparedStatement and ResultSet.
     *
     * @param ps The PreparedStatement to close.
     * @param rs The ResultSet to close.
     * @throws RuntimeException If there is an issue closing the PreparedStatement or ResultSet.
     */
    public void close(PreparedStatement ps, ResultSet rs) throws RuntimeException {
        try {
            if (ps != null) {
                ps.close();
            }

            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            RabbitMQ.sendMessage("Connection closing exception");
            logger.error("Connection closing exception", e);
            throw new RuntimeException("Connection closing exception", e);
        }
    }
}

