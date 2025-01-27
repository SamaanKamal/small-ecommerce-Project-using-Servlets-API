package com.example.ecommerce.Service.Category;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.ecommerce.DB.DatabaseUtil;
import com.example.ecommerce.Listener.ApplicationContextListener;
import com.example.ecommerce.Model.Category;

public class CategoryServiceImp implements CategoryService {
	
	public CategoryServiceImp() {
		super();
	}

	@Override
	public Category find(Integer id) throws SQLException {
        String sql = "SELECT * FROM categories WHERE id = ?";
        try (PreparedStatement stmt = ApplicationContextListener.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCategory(rs);
            }
            return null;
        }
    }
	
	@Override
    public List<Category> findAll() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        try (Statement stmt = ApplicationContextListener.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                categories.add(mapResultSetToCategory(rs));
            }
        }
        return categories;
    }
	
	@Override
    public void remove(Integer id) throws SQLException {
        String sql = "DELETE FROM categories WHERE id = ?";
        try (PreparedStatement stmt = ApplicationContextListener.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Category mapResultSetToCategory(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt("id"));
        category.setName(rs.getString("name"));
        category.setDescription(rs.getString("description"));
        category.setCreatedAt(rs.getTimestamp("created_at").toString());
        return category;
    }
}
