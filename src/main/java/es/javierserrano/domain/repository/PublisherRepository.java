package es.javierserrano.domain.repository;

import es.javierserrano.domain.repository.entity.PublisherEntity;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository {
    List<PublisherEntity> findAll();

    Optional<PublisherEntity> findById(Long id);

    Optional<PublisherEntity> findBySlug(String slug);

    PublisherEntity save(PublisherEntity publisherEntity);

    void deleteBySlug(String slug);
}
