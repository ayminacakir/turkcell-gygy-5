package com.turkcell.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.turkcell.library.dto.CreateStudentRequest;
import com.turkcell.library.dto.StudentResponse;
import com.turkcell.library.dto.UpdateStudentRequest;
import com.turkcell.library.entity.Student;
import com.turkcell.library.repository.StudentRepository;

@Service
public class StudentServiceImpl {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentResponse create(CreateStudentRequest request) {
        Student student = new Student();

        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setBirthdate(request.getBirthdate());

        student = studentRepository.save(student);

        return mapToResponse(student);
    }

    public List<StudentResponse> getAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponse> responseList = new ArrayList<>();

        for (Student student : students) {
            responseList.add(mapToResponse(student));
        }

        return responseList;
    }

    public StudentResponse getById(UUID id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı."));

        return mapToResponse(student);
    }

    public StudentResponse update(UUID id, UpdateStudentRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı."));

        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setBirthdate(request.getBirthdate());

        student = studentRepository.save(student);

        return mapToResponse(student);
    }

    public void delete(UUID id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı."));

        studentRepository.delete(student);
    }

    private StudentResponse mapToResponse(Student student) {
        StudentResponse response = new StudentResponse();

        response.setId(student.getId());
        response.setFirstName(student.getFirstName());
        response.setLastName(student.getLastName());
        response.setEmail(student.getEmail());
        response.setPhoneNumber(student.getPhoneNumber());
        response.setMembershipDate(student.getMembershipDate());
        response.setBirthdate(student.getBirthdate());

        return response;
    }
}