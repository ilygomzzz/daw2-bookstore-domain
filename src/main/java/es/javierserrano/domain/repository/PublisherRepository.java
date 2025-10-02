package es.javierserrano.domain.repository;

import es.javierserrano.domain.repository.entity.PublisherEntity;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository {

    List<PublisherEntity> findAll();

    Optional<PublisherEntity> findBySlug(String slug);

    PublisherEntity create(PublisherEntity publisherEntity);

    PublisherEntity update(PublisherEntity publisherEntity);

    int delete(String slug);
}
