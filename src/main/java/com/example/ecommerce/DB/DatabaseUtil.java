package com.example.ecommerce.DB;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {
    
    public static Connection getConnection() throws SQLException {
    	Properties properties = loadProperties();

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

        try (InputStream inputStream = DatabaseUtil.class.getClassLoader().getResourceAsStream("env.properties")) {
            if (inputStream == null) {
                throw new SQLException("Unable to find env.properties in the classpath");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new SQLException("Unable to load properties file", e);
        }
        
        
        return properties;
    }
}
