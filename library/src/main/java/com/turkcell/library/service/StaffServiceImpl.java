package com.turkcell.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.turkcell.library.dto.CreateStaffRequest;
import com.turkcell.library.dto.StaffResponse;
import com.turkcell.library.dto.UpdateStaffRequest;
import com.turkcell.library.entity.Staff;
import com.turkcell.library.repository.StaffRepository;

@Service
public class StaffServiceImpl {

    private final StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public StaffResponse create(CreateStaffRequest request) {
        Staff staff = new Staff();

        staff.setFirstName(request.getFirstName());
        staff.setLastName(request.getLastName());
        staff.setUsername(request.getUsername());
        staff.setPasswordHash(request.getPasswordHash());
        staff.setRole(request.getRole());

        staff = staffRepository.save(staff);

        return mapToResponse(staff);
    }

    public List<StaffResponse> getAll() {
        List<Staff> staffList = staffRepository.findAll();
        List<StaffResponse> responseList = new ArrayList<>();

        for (Staff staff : staffList) {
            responseList.add(mapToResponse(staff));
        }

        return responseList;
    }

    public StaffResponse getById(UUID id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personel bulunamadı."));

        return mapToResponse(staff);
    }

    public StaffResponse update(UUID id, UpdateStaffRequest request) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personel bulunamadı."));

        staff.setFirstName(request.getFirstName());
        staff.setLastName(request.getLastName());
        staff.setUsername(request.getUsername());
        staff.setPasswordHash(request.getPasswordHash());
        staff.setRole(request.getRole());

        staff = staffRepository.save(staff);

        return mapToResponse(staff);
    }

    public void delete(UUID id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personel bulunamadı."));

        staffRepository.delete(staff);
    }

    private StaffResponse mapToResponse(Staff staff) {
        StaffResponse response = new StaffResponse();

        response.setId(staff.getId());
        response.setFirstName(staff.getFirstName());
        response.setLastName(staff.getLastName());
        response.setUsername(staff.getUsername());
        response.setRole(staff.getRole());

        return response;
    }
}
