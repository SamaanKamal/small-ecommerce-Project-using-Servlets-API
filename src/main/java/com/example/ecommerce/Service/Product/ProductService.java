package com.example.ecommerce.Service.Product;

import java.sql.SQLException;
import java.util.List;

import com.example.ecommerce.Model.Product;

public interface ProductService {
	Product find(Integer id) throws SQLException;

    List<Product> findAll() throws SQLException;

    List<Product> findByCategory(Integer categoryId) throws SQLException;

    void remove(Integer id) throws SQLException;


}
