package es.javierserrano.domain.repository;

import es.javierserrano.domain.model.Page;
import es.javierserrano.domain.repository.entity.BookEntity;

import java.util.Optional;

public interface BookRepository {

    Page<BookEntity> findAll(int page, int size);

    Optional<BookEntity> findByIsbn(String isbn);

    BookEntity save(BookEntity bookEntity);

    Optional<BookEntity> findById(Long id);

    void deleteByIsbn(String isbn);
}
