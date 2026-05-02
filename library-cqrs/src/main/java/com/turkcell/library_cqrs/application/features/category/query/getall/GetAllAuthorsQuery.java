package com.turkcell.library_cqrs.application.features.category.query.getall;

import java.util.List;

import com.turkcell.library_cqrs.core.mediator.cqrs.Query;

// Bu query tüm yazarları listeleme isteğidir.
// İçinde parametre yok.
// Çalışınca List<ListAuthorResponse> döner.
public record GetAllAuthorsQuery() implements Query<List<ListAuthorResponse>> {
}
