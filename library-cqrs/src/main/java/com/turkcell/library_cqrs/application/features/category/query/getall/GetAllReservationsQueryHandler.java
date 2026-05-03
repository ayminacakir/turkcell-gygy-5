package com.turkcell.library_cqrs.application.features.category.query.getall;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.turkcell.library_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs.persistence.repositories.ReservationRepository;

@Component
public class GetAllReservationsQueryHandler
        implements QueryHandler<GetAllReservationsQuery, List<ListReservationResponse>> {

    private final ReservationRepository reservationRepository;

    public GetAllReservationsQueryHandler(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ListReservationResponse> handle(GetAllReservationsQuery query) {

        return reservationRepository.findAll()
                .stream()
                .map(reservation -> new ListReservationResponse(
                        reservation.getId(),

                        reservation.getBook().getId(),
                        reservation.getBook().getTitle(),

                        reservation.getStudent().getId(),
                        reservation.getStudent().getFirstName()
                                + " "
                                + reservation.getStudent().getLastName(),

                        reservation.getReservationDate(),
                        reservation.getStatus()))
                .toList();
    }
}
