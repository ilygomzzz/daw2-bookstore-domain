package es.javierserrano.domain.repository;

import es.javierserrano.domain.model.Page;
import es.javierserrano.domain.repository.entity.BookEntity;

import java.util.Optional;

public interface BookRepository {

    Page<BookEntity> findAll(int page, int pageSize);

    Optional<BookEntity> findByIsbn(String isbn);

    BookEntity create(BookEntity bookEntity);

    BookEntity update(BookEntity bookEntity);

    int delete(String isbn);
}
