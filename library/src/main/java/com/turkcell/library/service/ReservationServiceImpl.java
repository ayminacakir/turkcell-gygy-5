package com.turkcell.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.turkcell.library.dto.CreateReservationRequest;
import com.turkcell.library.dto.ReservationResponse;
import com.turkcell.library.dto.UpdateReservationRequest;
import com.turkcell.library.entity.Book;
import com.turkcell.library.entity.Reservation;
import com.turkcell.library.entity.Student;
import com.turkcell.library.repository.BookRepository;
import com.turkcell.library.repository.ReservationRepository;
import com.turkcell.library.repository.StudentRepository;

@Service
public class ReservationServiceImpl {

    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;

    public ReservationServiceImpl(
            ReservationRepository reservationRepository,
            BookRepository bookRepository,
            StudentRepository studentRepository) {
        this.reservationRepository = reservationRepository;
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
    }

    public ReservationResponse create(CreateReservationRequest request) {

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı."));

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı."));

        Reservation reservation = new Reservation();
        reservation.setBook(book);
        reservation.setStudent(student);
        reservation.setStatus("Pending");

        reservation = reservationRepository.save(reservation);

        return mapToResponse(reservation);
    }

    public List<ReservationResponse> getAll() {
        List<Reservation> list = reservationRepository.findAll();
        List<ReservationResponse> responseList = new ArrayList<>();

        for (Reservation r : list) {
            responseList.add(mapToResponse(r));
        }

        return responseList;
    }

    public ReservationResponse getById(UUID id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rezervasyon bulunamadı."));

        return mapToResponse(reservation);
    }

    public ReservationResponse update(UUID id, UpdateReservationRequest request) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rezervasyon bulunamadı."));

        reservation.setStatus(request.getStatus());

        reservation = reservationRepository.save(reservation);

        return mapToResponse(reservation);
    }

    public void delete(UUID id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rezervasyon bulunamadı."));

        reservationRepository.delete(reservation);
    }

    private ReservationResponse mapToResponse(Reservation reservation) {

        ReservationResponse response = new ReservationResponse();

        response.setId(reservation.getId());
        response.setBookTitle(reservation.getBook().getTitle());
        response.setStudentName(
                reservation.getStudent().getFirstName() + " " +
                        reservation.getStudent().getLastName());
        response.setReservationDate(reservation.getReservationDate());
        response.setStatus(reservation.getStatus());

        return response;
    }
}