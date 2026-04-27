package com.turkcell.library.entity;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @Column(name = "shelf_number", length = 20)
    private String shelfNumber;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "section", length = 50)
    private String section;

    @OneToMany(mappedBy = "location")
    private List<BookCopy> bookCopies;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(String shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public List<BookCopy> getBookCopies() {
        return bookCopies;
    }

    public void setBookCopies(List<BookCopy> bookCopies) {
        this.bookCopies = bookCopies;
    }
}