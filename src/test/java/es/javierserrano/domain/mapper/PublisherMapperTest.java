package es.javierserrano.domain.mapper;

import es.javierserrano.domain.model.Publisher;
import es.javierserrano.domain.repository.entity.PublisherEntity;
import es.javierserrano.domain.service.dto.PublisherDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublisherMapperTest {

    @Nested
    class PublisherEntityToPublisher {
        @Test
        @DisplayName("Given a valid PublisherEntity, when mapping to Publisher, then fields should match")
        void givenValidPublisherEntity_whenMappingToPublisher_thenFieldsShouldMatch() {
            PublisherEntity publisherEntity = new PublisherEntity(1L, "Penguin Random House", "penguin-random-house");
            Publisher publisher = PublisherMapper.getInstance().fromPublisherEntityToPublisher(publisherEntity);

            assertAll(
                    () -> assertEquals(publisherEntity.name(), publisher.getName()),
                    () -> assertEquals(publisherEntity.slug(), publisher.getSlug())
            );
        }

        @Test
        @DisplayName("Given a null PublisherEntity, when mapping to Publisher, then should return null")
        void givenNullPublisherEntity_whenMappingToPublisher_thenShouldReturnNull() {
            assertNull(PublisherMapper.getInstance().fromPublisherEntityToPublisher(null));
        }
    }

    @Nested
    class PublisherToPublisherEntity {
        @Test
        @DisplayName("Given a valid Publisher, when mapping to PublisherEntity, then fields should match")
        void givenValidPublisher_whenMappingToPublisherEntity_thenFieldsShouldMatch() {
            Publisher publisher = new es.javierserrano.domain.model.Publisher(1L, "HarperCollins", "harpercollins");
            PublisherEntity publisherEntity = PublisherMapper.getInstance().fromPublisherToPublisherEntity(publisher);
            assertAll(
                    () -> assertEquals(publisher.getName(), publisherEntity.name()),
                    () -> assertEquals(publisher.getSlug(), publisherEntity.slug())
            );
        }

        @Test
        @DisplayName("Given a null Publisher, when mapping to PublisherEntity, then BusinessException should be thrown")
        void givenNullPublisher_whenMappingToPublisherEntity_thenShouldReturnNull() {
            assertNull(PublisherMapper.getInstance().fromPublisherToPublisherEntity(null));
        }
    }

    @Nested
    class PublisherToPublisherDto {
        @Test
        @DisplayName("Given a valid Publisher, when mapping to PublisherDto, then fields should match")
        void givenValidPublisher_whenMappingToPublisherDto_thenFieldsShouldMatch() {
            Publisher publisher = new Publisher(1L, "Simon & Schuster", "simon-schuster");
            PublisherDto publisherDto = PublisherMapper.getInstance().fromPublisherToPublisherDto(publisher);
            assertAll(
                    () -> assertEquals(publisher.getName(), publisherDto.name()),
                    () -> assertEquals(publisher.getSlug(), publisherDto.slug())
            );
        }

        @Test
        @DisplayName("Given a null Publisher, when mapping to PublisherDto, then BusinessException should be thrown")
        void givenNullPublisher_whenMappingToPublisherDto_thenShouldReturnNull() {
            assertNull(PublisherMapper.getInstance().fromPublisherToPublisherDto(null));
        }
    }

    @Nested
    class PublisherDtoToPublisher {
        @Test
        @DisplayName("Given a valid PublisherDto, when mapping to Publisher, then fields should match")
        void givenValidPublisherDto_whenMappingToPublisher_thenFieldsShouldMatch() {
            var publisherDto = new PublisherDto(1L,"Macmillan Publishers", "macmillan-publishers");
            Publisher publisher = PublisherMapper.getInstance().fromPublisherDtoToPublisher(publisherDto);
            assertAll(
                    () -> assertEquals(publisherDto.name(), publisher.getName()),
                    () -> assertEquals(publisherDto.slug(), publisher.getSlug())
            );
        }

        @Test
        @DisplayName("Given a null PublisherDto, when mapping to Publisher, then BusinessException should be thrown")
        void givenNullPublisherDto_whenMappingToPublisher_thenShouldReturnNull() {
            assertNull(PublisherMapper.getInstance().fromPublisherDtoToPublisher(null));
        }
    }

}