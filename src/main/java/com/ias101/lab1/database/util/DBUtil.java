package com.ias101.lab1.database.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Utility class for database connection management.
 */
public class DBUtil {
    /** Database connection object */
    private Connection conn;

    /**
     * Establishes a connection to the database using the provided credentials.
     *
     * @param url      The JDBC URL of the database
     * @param username The username for database authentication
     * @param password The password for database authentication
     * @return A Connection object representing the database connection
     * @throws RuntimeException if connection cannot be established
     */
    public static Connection connect(String url, String username, String password) {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("Could not establish connection to the database. Please check the URL, username, and password provided.");
        }
    }

    /**
     * Closes the provided database connection.
     *
     * @param conn The Connection object to be closed
     * @throws RuntimeException if connection cannot be closed properly
     */
    public void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}