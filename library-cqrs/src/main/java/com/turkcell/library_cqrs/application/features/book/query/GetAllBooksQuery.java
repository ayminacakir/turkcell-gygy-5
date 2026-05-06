package com.turkcell.library_cqrs.application.features.book.query;

import java.util.List;

import com.turkcell.library_cqrs.core.mediator.cqrs.Query;

public record GetAllBooksQuery() implements Query<List<ListBookResponse>> {

}
