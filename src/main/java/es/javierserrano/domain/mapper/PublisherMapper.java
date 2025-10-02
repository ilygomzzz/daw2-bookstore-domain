package es.javierserrano.domain.mapper;

import es.javierserrano.domain.model.Publisher;
import es.javierserrano.domain.repository.entity.PublisherEntity;
import es.javierserrano.domain.service.dto.PublisherDto;

public class PublisherMapper {
    private static PublisherMapper instance;

    private PublisherMapper() {}

    public static PublisherMapper getInstance() {
        if (instance == null) {
            instance = new PublisherMapper();
        }

        return instance;
    }

    public Publisher fromPublisherEntityToPublisher(PublisherEntity publisherEntity) {
        if (publisherEntity == null) {
            return null;
        }

        return new Publisher(
                publisherEntity.id(),
                publisherEntity.name(),
                publisherEntity.slug()
        );
    }

    public PublisherEntity fromPublisherToPublisherEntity(Publisher publisher) {
        if (publisher == null) {
            return null;
        }

        return new PublisherEntity(
                publisher.getId(),
                publisher.getName(),
                publisher.getSlug()
        );
    }

    public PublisherDto fromPublisherToPublisherDto(Publisher publisher) {
        if (publisher == null) {
            return  null;
        }
        return new PublisherDto(
                publisher.getId(),
                publisher.getName(),
                publisher.getSlug()
        );
    }

    public Publisher fromPublisherDtoToPublisher(PublisherDto publisherDto) {
        if (publisherDto == null) {
           return null;
        }
        return new Publisher(
                publisherDto.id(),
                publisherDto.name(),
                publisherDto.slug()
        );
    }
}
