package es.javierserrano.domain.service.impl;

import es.javierserrano.domain.exception.ResourceNotFoundException;
import es.javierserrano.domain.mapper.PublisherMapper;
import es.javierserrano.domain.model.Publisher;
import es.javierserrano.domain.repository.PublisherRepository;
import es.javierserrano.domain.repository.entity.PublisherEntity;
import es.javierserrano.domain.service.PublisherService;
import es.javierserrano.domain.service.dto.PublisherDto;

import java.util.List;

public class PublisherServiceImpl implements PublisherService {
    private PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<PublisherDto> getAll() {
        return publisherRepository
                .findAll()
                .stream()
                .map(PublisherMapper.getInstance()::fromPublisherEntityToPublisher)
                .map(PublisherMapper.getInstance()::fromPublisherToPublisherDto)
                .toList();
    }

    @Override
    public PublisherDto getBySlug(String slug) {
        return publisherRepository
                .findBySlug(slug)
                .map(PublisherMapper.getInstance()::fromPublisherEntityToPublisher)
                .map(PublisherMapper.getInstance()::fromPublisherToPublisherDto)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher with Slug " + slug + " not found"));
    }

    @Override
    public PublisherDto create(PublisherDto publisherDto) {
        Publisher publisher = PublisherMapper.getInstance().fromPublisherDtoToPublisher(publisherDto);
        PublisherEntity publisherEntity = PublisherMapper.getInstance().fromPublisherToPublisherEntity(publisher);
        PublisherEntity newPublisherEntity = publisherRepository.create(publisherEntity);
        Publisher newPubliser = PublisherMapper.getInstance().fromPublisherEntityToPublisher(newPublisherEntity);
        return PublisherMapper.getInstance().fromPublisherToPublisherDto(newPubliser);
    }

    @Override
    public PublisherDto update(PublisherDto publisherDto) {
        Publisher publisher = PublisherMapper.getInstance().fromPublisherDtoToPublisher(publisherDto);
        PublisherEntity publisherEntity = PublisherMapper.getInstance().fromPublisherToPublisherEntity(publisher);
        PublisherEntity newPublisherEntity = publisherRepository.update(publisherEntity);
        Publisher newPubliser = PublisherMapper.getInstance().fromPublisherEntityToPublisher(newPublisherEntity);
        return PublisherMapper.getInstance().fromPublisherToPublisherDto(newPubliser);
    }

    @Override
    public int delete(String slug) {
        return publisherRepository.delete(slug);
    }
}
