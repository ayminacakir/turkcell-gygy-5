package com.turkcell.library_cqrs.application.features.category.command.create;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs.domain.entities.Location;
import com.turkcell.library_cqrs.persistence.repositories.LocationRepository;

@Component
public class CreateLocationCommandHandler implements CommandHandler<CreateLocationCommand, UUID> {

    private final LocationRepository locationRepository;

    public CreateLocationCommandHandler(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public UUID handle(CreateLocationCommand command) {

        Location location = new Location();
        location.setShelfNumber(command.shelfNumber());
        location.setFloor(command.floor());
        location.setSection(command.section());

        Location savedLocation = locationRepository.save(location);

        return savedLocation.getId();
    }
}
