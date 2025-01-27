package com.example.ecommerce.Listener;

import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.ServletContextEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.example.ecommerce.DB.DatabaseInitializer;
import com.example.ecommerce.DB.DatabaseUtil;

import java.io.IOException;
import java.io.InputStream;

@WebListener
public class ApplicationContextListener implements ServletContextListener{
	private static Connection connection;
    private static Properties dbProperties;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // Load properties first
            loadProperties();
            
            // Initialize database connection
            initializeConnection();
            
            // Initialize sample data
            DatabaseInitializer.initializeData(connection);
            
            System.out.println("Database connection initialized successfully");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize database connection", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed successfully");
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }

    private void loadProperties() throws SQLException {
        dbProperties = new Properties();
        try (InputStream inputStream = ApplicationContextListener.class.getClassLoader().getResourceAsStream("env.properties")) {
            if (inputStream == null) {
                throw new SQLException("Unable to find env.properties in the classpath");
            }
            dbProperties.load(inputStream);
        } catch (IOException e) {
            throw new SQLException("Unable to load properties file", e);
        }
    }

    private void initializeConnection() throws SQLException {
        String URL = dbProperties.getProperty("DB_URL", "jdbc:mysql://localhost:3306/ecommerce");
        String USER = dbProperties.getProperty("DB_USER", "root");
        String PASSWORD = dbProperties.getProperty("DB_PASSWORD", "password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Database connection is not initialized");
        }
        return connection;
    }
}
