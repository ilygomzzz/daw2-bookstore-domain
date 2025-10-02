package es.javierserrano.domain.repository;

import es.javierserrano.domain.repository.entity.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    List<AuthorEntity> getAll();

    Optional<AuthorEntity> findBySlug(String slug);

    AuthorEntity create(AuthorEntity authorEntity);

    AuthorEntity update(AuthorEntity authorEntity);

    int delete(String slug);
}
