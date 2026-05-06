package com.turkcell.library_cqrs.application.features.reservation.query;

import java.util.List;

import com.turkcell.library_cqrs.core.mediator.cqrs.Query;

public record GetAllReservationsQuery() implements Query<List<ListReservationResponse>> {
}
