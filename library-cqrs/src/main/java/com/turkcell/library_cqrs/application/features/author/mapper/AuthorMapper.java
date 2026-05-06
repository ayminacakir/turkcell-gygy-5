package com.turkcell.library_cqrs.application.features.author.mapper;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.application.features.author.command.CreateAuthorCommand;
import com.turkcell.library_cqrs.application.features.author.query.ListAuthorResponse;
import com.turkcell.library_cqrs.domain.entities.Author;

@Component
public class AuthorMapper {

    public Author authorFromCreateCommand(CreateAuthorCommand command) {
        Author author = new Author();
        author.setFirstname(command.firstname());
        author.setLastname(command.lastname());
        author.setBiography(command.biography());
        return author;
    }

    public ListAuthorResponse listAuthorResponseFromAuthor(Author author) {
        return new ListAuthorResponse(
                author.getId(),
                author.getFirstname(),
                author.getLastname(),
                author.getBiography());
    }
}
