package com.turkcell.library_cqrs.application.features.fine.query;

import java.util.List;

import com.turkcell.library_cqrs.core.mediator.cqrs.Query;

public record GetAllFinesQuery() implements Query<List<ListFineResponse>> {
}