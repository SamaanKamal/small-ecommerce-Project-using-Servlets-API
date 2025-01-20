package com.example.ecommerce.Service.Category;

import java.sql.SQLException;
import java.util.List;

import com.example.ecommerce.Model.Category;

public interface CategoryService {
	Category find(Integer id) throws SQLException;

    List<Category> findAll() throws SQLException;

    void remove(Integer id) throws SQLException;
}
