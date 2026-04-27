package com.turkcell.library.dto;

import java.time.LocalDate;
import java.util.UUID;

public class LoanResponse {
    private UUID id;
    private String bookTitle;
    private String studentName;
    private String staffName;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String bookCopyStatus;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getBookCopyStatus() {
        return bookCopyStatus;
    }

    public void setBookCopyStatus(String bookCopyStatus) {
        this.bookCopyStatus = bookCopyStatus;
    }

}