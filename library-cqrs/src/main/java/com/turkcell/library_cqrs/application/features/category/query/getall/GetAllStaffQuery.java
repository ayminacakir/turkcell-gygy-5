package com.turkcell.library_cqrs.application.features.category.query.getall;

import java.util.List;
import com.turkcell.library_cqrs.core.mediator.cqrs.Query;

public record GetAllStaffQuery() implements Query<List<ListStaffResponse>> {
}
