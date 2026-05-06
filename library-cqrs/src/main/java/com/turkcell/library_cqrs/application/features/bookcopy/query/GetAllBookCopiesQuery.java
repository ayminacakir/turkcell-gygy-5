package com.turkcell.library_cqrs.application.features.bookcopy.query;

import java.util.List;

import com.turkcell.library_cqrs.core.mediator.cqrs.Query;

public record GetAllBookCopiesQuery() implements Query<List<ListBookCopyResponse>> {
}