package com.example.ecommerce.Service.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.ecommerce.DB.DatabaseUtil;
import com.example.ecommerce.Model.Category;

public interface CategoryService {
	Category find(Integer id) throws SQLException;

    List<Category> findAll() throws SQLException;

    void remove(Integer id) throws SQLException;
}
