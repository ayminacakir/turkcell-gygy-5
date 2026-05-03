package com.turkcell.library_cqrs.application.features.category.command.create;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs.domain.entities.Staff;
import com.turkcell.library_cqrs.persistence.repositories.StaffRepository;

@Component
public class CreateStaffCommandHandler implements CommandHandler<CreateStaffCommand, UUID> {

    private final StaffRepository staffRepository;

    public CreateStaffCommandHandler(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public UUID handle(CreateStaffCommand command) {

        if (staffRepository.existsByUsername(command.username())) {
            throw new RuntimeException("Bu kullanıcı adı zaten kullanılıyor.");
        }

        Staff staff = new Staff();
        staff.setFirstName(command.firstName());
        staff.setLastName(command.lastName());
        staff.setUsername(command.username());
        staff.setPasswordHash(command.passwordHash());
        staff.setRole(command.role());

        Staff savedStaff = staffRepository.save(staff);

        return savedStaff.getId();
    }
}