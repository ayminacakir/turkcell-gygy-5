package com.turkcell.library_cqrs.application.features.staff.query;

import java.util.List;

import com.turkcell.library_cqrs.core.mediator.cqrs.Query;

public record GetAllStaffQuery() implements Query<List<ListStaffResponse>> {
}
