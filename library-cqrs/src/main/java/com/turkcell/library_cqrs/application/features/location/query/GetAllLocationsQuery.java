package com.turkcell.library_cqrs.application.features.location.query;

import java.util.List;

import com.turkcell.library_cqrs.core.mediator.cqrs.Query;

public record GetAllLocationsQuery() implements Query<List<ListLocationResponse>> {
}