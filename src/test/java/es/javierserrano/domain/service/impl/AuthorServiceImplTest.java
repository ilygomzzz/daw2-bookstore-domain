package es.javierserrano.domain.service.impl;

import es.javierserrano.domain.data.loader.AuthorsDataLoader;
import es.javierserrano.domain.exception.BusinessException;
import es.javierserrano.domain.model.Author;
import es.javierserrano.domain.repository.AuthorRepository;
import es.javierserrano.domain.repository.entity.AuthorEntity;
import es.javierserrano.domain.service.dto.AuthorDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    private static List<Author> authors;
    private static List<AuthorDto> authorDtos;
    private static List<AuthorEntity> authorEntities;

    @BeforeAll
    static void setUp() {
        AuthorsDataLoader authorsDataLoader = new AuthorsDataLoader();
        authors = authorsDataLoader.loadAuthorsFromCSV();
        authorDtos = authorsDataLoader.loadAuthorDtosFromCsv();
        authorEntities = authorsDataLoader.loadAuthorEntitiesFromCSV();
    }

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorServiceImpl;

    @Test
    @DisplayName("GetAllShouldReturnListOfAuthorDtos")
    void getAllShouldReturnListOfAuthorDtos() {
        when(authorRepository.getAll()).thenReturn(authorEntities);
        List<AuthorDto> result = authorServiceImpl.getAll();
        assertEquals(authorDtos, result);
    }

    @Nested
    class GetBySlugTests {
        @Test
        @DisplayName("GetBySlugShouldReturnAuthorDtoWhenSlugExists")
        void getBySlugShouldReturnAuthorDtoWhenSlugExists() {
            String slug = authorEntities.get(0).slug();
            when(authorRepository.findBySlug(slug)).thenReturn(Optional.of(authorEntities.get(0)));

            AuthorDto result = authorServiceImpl.getBySlug(slug);

            assertTrue(authorDtos.contains(result));
        }

        @Test
        @DisplayName("GetBySlugShouldThrowExceptionWhenSlugDoesNotExist")
        void getBySlugShouldThrowExceptionWhenSlugDoesNotExist() {
            String slug = "non-existing-slug";
            when(authorRepository.findBySlug(slug)).thenReturn(Optional.empty());
            assertThrows(RuntimeException.class, () -> authorServiceImpl.getBySlug(slug));
        }
    }

    @Nested
    class CreateAuthorTests {
        @Test
        @DisplayName("CreateShouldReturnCreatedAuthorDto")
        void createShouldReturnCreatedAuthorDto() {
            AuthorDto authorDto = authorDtos.get(0);
            AuthorEntity authorEntity = authorEntities.get(0);
            when(authorRepository.create(authorEntity)).thenReturn(authorEntity);

            AuthorDto result = authorServiceImpl.create(authorDto);

            assertEquals(result, authorDto);
        }

        @Test
        @DisplayName("CreateNullAuthorShouldThrowException")
        void createNullAuthorShouldThrowException() {
            assertThrows(BusinessException.class, () -> authorServiceImpl.create(null));
        }

        @Test
        @DisplayName("CreateAuthorWithExistingSlugShouldThrowException")
        void createAuthorWithExistingSlugShouldThrowException() {
            AuthorDto authorDto = authorDtos.get(0);
            AuthorEntity authorEntity = authorEntities.get(0);
            when(authorRepository.findBySlug(authorDto.slug())).thenReturn(Optional.of(authorEntity));
            assertThrows(BusinessException.class, () -> authorServiceImpl.create(authorDto));
        }
    }

    @Nested
    class UpdateAuthorTests {
        @Test
        @DisplayName("UpdateShouldUpdateAuthorDto")
        void updateShouldUpdateAuthorDto() {
            AuthorDto authorDto = authorDtos.get(0);
            AuthorEntity authorEntity = authorEntities.get(0);


        }
    }

    // Additional tests for update and delete methods can be added similarly
}