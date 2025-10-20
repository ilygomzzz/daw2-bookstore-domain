package es.javierserrano.domain.service;

import es.javierserrano.domain.service.dto.PublisherDto;

import java.util.List;

public interface PublisherService {
    List<PublisherDto> getAll(int page, int size);

    PublisherDto getBySlug(String slug);

    PublisherDto create(PublisherDto publisherDto);

    PublisherDto update(PublisherDto publisherDto);

    void deleteBySlug(String slug);
}
