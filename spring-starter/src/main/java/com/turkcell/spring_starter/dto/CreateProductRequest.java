package com.turkcell.spring_starter.dto;

import java.util.Set;
import java.util.UUID;

public class CreateProductRequest {

    private String name;
    private String description;

    // Product bir kategoriye bağlı olduğu için categoryId alıyoruz
    private UUID categoryId;

    // Kullanıcı ürün oluştururken tag id'lerini gönderecek.
    private Set<UUID> tagIds;

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

    public Set<UUID> getTagIds() {
        return tagIds;
    }

    public void setTagIds(Set<UUID> tagIds) {
        this.tagIds = tagIds;
    }
}