package com.example.ecommerce.DB;

import java.sql.*;

public class DatabaseInitializer {
	private static final String CHECK_CATEGORIES = "SELECT COUNT(*) FROM categories";
    
	public static void initializeData(Connection conn) {
        try {
            if (isDataInitializationNeeded(conn)) {
                insertCategories(conn);
                insertProducts(conn);
                System.out.println("Sample data initialized successfully!");
            } else {
                System.out.println("Database already contains data, skipping initialization.");
            }
        } catch (SQLException e) {
            System.err.println("Error initializing data: " + e.getMessage());
        }
    }
    
	private static boolean isDataInitializationNeeded(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(CHECK_CATEGORIES)) {
            rs.next();
            return rs.getInt(1) == 0;
        }
    }
    
    private static void insertCategories(Connection conn) throws SQLException {
        String sql = "INSERT INTO categories (name, description) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Electronics
            stmt.setString(1, "Electronics");
            stmt.setString(2, "Electronic devices and accessories");
            stmt.executeUpdate();
            
            // Books
            stmt.setString(1, "Books");
            stmt.setString(2, "Physical and digital books");
            stmt.executeUpdate();
            
            // Clothing
            stmt.setString(1, "Clothing");
            stmt.setString(2, "Men's and women's apparel");
            stmt.executeUpdate();
        }
    }
    
    private static void insertProducts(Connection conn) throws SQLException {
        String sql = "INSERT INTO products (category_id, name, description, price, stock) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Electronics (category_id = 1)
            stmt.setInt(1, 1);
            stmt.setString(2, "Smartphone");
            stmt.setString(3, "Latest model smartphone");
            stmt.setDouble(4, 699.99);
            stmt.setInt(5, 50);
            stmt.executeUpdate();

            stmt.setInt(1, 1);
            stmt.setString(2, "Laptop");
            stmt.setString(3, "High-performance laptop");
            stmt.setDouble(4, 1299.99);
            stmt.setInt(5, 30);
            stmt.executeUpdate();

            // Books (category_id = 2)
            stmt.setInt(1, 2);
            stmt.setString(2, "Java Programming");
            stmt.setString(3, "Complete guide to Java");
            stmt.setDouble(4, 49.99);
            stmt.setInt(5, 75);
            stmt.executeUpdate();

            stmt.setInt(1, 2);
            stmt.setString(2, "Web Development");
            stmt.setString(3, "Full-stack web development");
            stmt.setDouble(4, 59.99);
            stmt.setInt(5, 60);
            stmt.executeUpdate();

            // Clothing (category_id = 3)
            stmt.setInt(1, 3);
            stmt.setString(2, "T-Shirt");
            stmt.setString(3, "Cotton casual t-shirt");
            stmt.setDouble(4, 19.99);
            stmt.setInt(5, 200);
            stmt.executeUpdate();

            stmt.setInt(1, 3);
            stmt.setString(2, "Jeans");
            stmt.setString(3, "Classic blue jeans");
            stmt.setDouble(4, 49.99);
            stmt.setInt(5, 150);
            stmt.executeUpdate();
        }
    }
}
