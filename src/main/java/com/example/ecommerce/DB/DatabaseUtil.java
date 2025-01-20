package com.example.ecommerce.DB;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {
	private static final String PROPERTIES_FILE = "src/main/resources/env.properties";
//	private static final String URL = "jdbc:mysql://localhost:3306/ecommerce";
//    private static final String USER = "root";
//    private static final String PASSWORD = "password";
    
    

    public static Connection getConnection() throws SQLException {
    	Properties properties = loadProperties();

        // Fetch database connection details from properties
        String URL = properties.getProperty("DB_URL", "jdbc:mysql://localhost:3306/ecommerce"); // Default value if not set
        String USER = properties.getProperty("DB_USER", "root"); // Default value
        String PASSWORD = properties.getProperty("DB_PASSWORD", "password"); // Default value

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }
    
    private static Properties loadProperties() throws SQLException {
        Properties properties = new Properties();

        // Load the properties file
        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new SQLException("Unable to load properties file", e);
        }

        return properties;
    }
}
