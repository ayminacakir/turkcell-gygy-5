package com.turkcell.library.dto;

import java.util.UUID;

public class CreateReservationRequest {

    private UUID bookId;
    private UUID studentId;

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

}