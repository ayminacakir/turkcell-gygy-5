package com.turkcell.library_cqrs.application.features.category.command.create;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs.domain.entities.Student;
import com.turkcell.library_cqrs.persistence.repositories.StudentRepository;

@Component // Bu olmazsa SpringMediator bu handler’ı bulamaz. Çünkü mediator handler’ları
           // Spring container içinde arıyor.
public class CreateStudentCommandHandler implements CommandHandler<CreateStudentCommand, UUID> {

    private final StudentRepository studentRepository;

    public CreateStudentCommandHandler(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UUID handle(CreateStudentCommand command) {

        if (studentRepository.existsByEmail(command.email())) {
            throw new RuntimeException("Bu email ile kayıtlı bir öğrenci zaten var.");
        }

        Student student = new Student();
        student.setFirstName(command.firstName());
        student.setLastName(command.lastName());
        student.setEmail(command.email());
        student.setPhoneNumber(command.phoneNumber());
        student.setBirthdate(command.birthdate());

        Student savedStudent = studentRepository.save(student);

        return savedStudent.getId();
    }
}