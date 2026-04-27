package com.turkcell.library.dto;

import java.util.Set;
import java.util.UUID;

public class CreateBookRequest {

    private String isbn;
    private String title;
    private String publisher;
    private Integer publicationYear;

    private Set<UUID> authorIds;
    private Set<UUID> categoryIds;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Set<UUID> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(Set<UUID> authorIds) {
        this.authorIds = authorIds;
    }

    public Set<UUID> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(Set<UUID> categoryIds) {
        this.categoryIds = categoryIds;
    }

}
