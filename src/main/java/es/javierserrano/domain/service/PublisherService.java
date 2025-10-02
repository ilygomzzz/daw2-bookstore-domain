package es.javierserrano.domain.service;

import es.javierserrano.domain.service.dto.PublisherDto;

import java.util.List;

public interface PublisherService {
    List<PublisherDto> getAll();

    PublisherDto getBySlug(String slug);

    PublisherDto create(PublisherDto publisherDto);

    PublisherDto update(PublisherDto publisherDto);

    int delete(String slug);
}
