package com.turkcell.library_cqrs.application.features.category.query.getall;

import java.util.List;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs.persistence.repositories.StaffRepository;

@Component
public class GetAllStaffQueryHandler implements QueryHandler<GetAllStaffQuery, List<ListStaffResponse>> {

    private final StaffRepository staffRepository;

    public GetAllStaffQueryHandler(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public List<ListStaffResponse> handle(GetAllStaffQuery query) {

        return staffRepository.findAll()
                .stream()
                .map(staff -> new ListStaffResponse(
                        staff.getId(),
                        staff.getFirstName(),
                        staff.getLastName(),
                        staff.getUsername(),
                        staff.getRole()))
                .toList();
    }
}