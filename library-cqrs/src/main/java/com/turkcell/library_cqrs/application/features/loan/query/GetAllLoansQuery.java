package com.turkcell.library_cqrs.application.features.loan.query;

import java.util.List;

import com.turkcell.library_cqrs.core.mediator.cqrs.Query;

public record GetAllLoansQuery() implements Query<List<ListLoanResponse>> {
}
