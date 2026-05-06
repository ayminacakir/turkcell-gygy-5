package com.turkcell.library_cqrs.application.features.student.query;

import java.util.List;

import com.turkcell.library_cqrs.core.mediator.cqrs.Query;

public record GetAllStudentsQuery() implements Query<List<ListStudentResponse>> {

}
