package com.turkcell.spring_starter.dto;

import java.util.UUID;

public class CreateProductRequest {

    private String name;
    private String description;

    // Product bir kategoriye bağlı olduğu için categoryId alıyoruz
    private UUID categoryId;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }
}