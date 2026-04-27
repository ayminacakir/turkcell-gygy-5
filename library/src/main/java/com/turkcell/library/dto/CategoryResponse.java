package com.turkcell.library.dto;

import java.util.UUID;

public class CategoryResponse {
    private UUID id;
    private String categoryName;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;

    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
