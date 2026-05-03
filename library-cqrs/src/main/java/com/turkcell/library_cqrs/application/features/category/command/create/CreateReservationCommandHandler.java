package com.turkcell.library_cqrs.application.features.category.command.create;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs.domain.entities.Book;
import com.turkcell.library_cqrs.domain.entities.Reservation;
import com.turkcell.library_cqrs.domain.entities.Student;
import com.turkcell.library_cqrs.persistence.repositories.BookRepository;
import com.turkcell.library_cqrs.persistence.repositories.ReservationRepository;
import com.turkcell.library_cqrs.persistence.repositories.StudentRepository;

@Component
public class CreateReservationCommandHandler implements CommandHandler<CreateReservationCommand, UUID> {

    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;

    public CreateReservationCommandHandler(
            ReservationRepository reservationRepository,
            BookRepository bookRepository,
            StudentRepository studentRepository) {
        this.reservationRepository = reservationRepository;
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public UUID handle(CreateReservationCommand command) {

        Book book = bookRepository.findById(command.bookId())
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı."));

        Student student = studentRepository.findById(command.studentId())
                .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı."));

        Reservation reservation = new Reservation();
        reservation.setBook(book);
        reservation.setStudent(student);
        reservation.setReservationDate(LocalDateTime.now());
        reservation.setStatus("PENDING");

        Reservation savedReservation = reservationRepository.save(reservation);

        return savedReservation.getId();
    }
}