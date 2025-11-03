package es.javierserrano.domain.repository;

import es.javierserrano.domain.model.Page;
import es.javierserrano.domain.repository.entity.PublisherEntity;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository {
    Page<PublisherEntity> findAll(int page, int size);

    Optional<PublisherEntity> findById(Long id);

    Optional<PublisherEntity> findBySlug(String slug);

    PublisherEntity save(PublisherEntity publisherEntity);

    void deleteBySlug(String slug);
}
