package com.example.ecommerce.Model;

import java.sql.Timestamp;

public class Category {
	private Integer id;
    private String name;
    private String description;
    private String createdAt;
    
    
	public Category() {
		super();
	}


	public Category(String name, String description, String createdAt) {
		super();
		this.name = name;
		this.description = description;
		this.createdAt = createdAt;
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


	public String getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}


	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description=" + description + ", createdAt=" + createdAt
				+ "]";
	}
    
    
}
