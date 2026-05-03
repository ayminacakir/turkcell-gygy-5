package com.turkcell.library_cqrs.application.features.category.query.getall;

import java.util.List;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs.persistence.repositories.StudentRepository;

@Component
public class GetAllStudentsQueryHandler implements QueryHandler<GetAllStudentsQuery, List<ListStudentResponse>> {
    private final StudentRepository studentRepository;

    public GetAllStudentsQueryHandler(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<ListStudentResponse> handle(GetAllStudentsQuery query) {
        return studentRepository.findAll()
                .stream().map(student -> new ListStudentResponse(
                        student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail(),
                        student.getPhoneNumber(),
                        student.getMembershipDate(),
                        student.getBirthdate()))
                .toList();
    }

}
