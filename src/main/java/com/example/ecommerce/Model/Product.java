package com.example.ecommerce.Model;

import java.sql.Timestamp;

public class Product {
	private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String createdAt;
    private Integer categoryId;
    
    
	public Product() {
		super();
	}


	public Product(String name, String description, Double price, Integer stock, String createdAt,
			Integer categoryId) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.createdAt = createdAt;
		this.categoryId = categoryId;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Integer getStock() {
		return stock;
	}


	public void setStock(Integer stock) {
		this.stock = stock;
	}


	public String getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}


	public Integer getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", stock="
				+ stock + ", createdAt=" + createdAt + ", categoryId=" + categoryId + "]";
	}
    
    
	
}
