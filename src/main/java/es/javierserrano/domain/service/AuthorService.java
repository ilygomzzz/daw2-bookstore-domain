package es.javierserrano.domain.service;

import es.javierserrano.domain.service.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> getAll();

    AuthorDto getBySlug(String slug);

    AuthorDto create(AuthorDto authorDto);

    AuthorDto update(AuthorDto authorDto);

    int delete(String slug);
}
