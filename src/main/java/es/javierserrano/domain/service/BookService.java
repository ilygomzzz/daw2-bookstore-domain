package es.javierserrano.domain.service;

import es.javierserrano.domain.model.Page;
import es.javierserrano.domain.service.dto.BookDto;

import java.util.Optional;

public interface BookService {
    Page<BookDto> getAll(int page, int size);

    BookDto getByIsbn(String isbn);

    Optional<BookDto> findByIsbn(String isbn);

    BookDto create(BookDto bookDto);

    BookDto update(BookDto bookDto);

    int delete(String isbn);
}
