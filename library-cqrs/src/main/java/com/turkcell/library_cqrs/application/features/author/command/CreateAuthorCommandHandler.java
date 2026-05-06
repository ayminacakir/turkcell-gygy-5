package com.turkcell.library_cqrs.application.features.author.command;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.application.features.author.mapper.AuthorMapper;
import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs.domain.entities.Author;
import com.turkcell.library_cqrs.persistence.repositories.AuthorRepository;

@Component
public class CreateAuthorCommandHandler implements CommandHandler<CreateAuthorCommand, UUID> {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public CreateAuthorCommandHandler(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public UUID handle(CreateAuthorCommand command) {
        Author author = authorMapper.authorFromCreateCommand(command);

        // Command'dan gelen verilerle yeni Author entity oluşturuyoruz.
        /*
         * Author author = new Author();
         * author.setFirstname(command.firstname());
         * author.setLastname(command.lastname());
         * author.setBiography(command.biography());
         */

        Author savedAuthor = authorRepository.save(author);

        // Oluşan yazarın id'sini dışarıya dönüyoruz.
        return savedAuthor.getId();
    }
}