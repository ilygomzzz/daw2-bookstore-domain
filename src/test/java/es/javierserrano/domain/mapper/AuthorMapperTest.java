package es.javierserrano.domain.mapper;

import es.javierserrano.domain.exception.BusinessException;
import es.javierserrano.domain.exception.ValidationException;
import es.javierserrano.domain.model.Author;
import es.javierserrano.domain.model.shared.Name;
import es.javierserrano.domain.model.shared.Slug;
import es.javierserrano.domain.repository.entity.AuthorEntity;
import es.javierserrano.domain.service.dto.AuthorDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorMapperTest {

    @Nested
    class testFromAuthorEntityToAuthor {
        @Test
        @DisplayName("Given AuthorEntity should return Author")
        void fromAuthorEntityToAuthorTest() {
            AuthorEntity authorEntity = new AuthorEntity(1L, "name", "nationality", "biographyEs", "biographyEn", 1990, 2020, "slug");

            Author author = AuthorMapper.getInstance().fromAuthorEntityToAuthor(authorEntity);

            assertAll(
                    () -> assertEquals(authorEntity.name(), author.getName()),
                    () -> assertEquals(authorEntity.nationality(), author.getNationality()),
                    () -> assertEquals(authorEntity.biographyEs(), author.getBiographyEs()),
                    () -> assertEquals(authorEntity.biographyEn(), author.getBiographyEn()),
                    () -> assertEquals(authorEntity.birthYear(), author.getBirthYear()),
                    () -> assertEquals(authorEntity.deathYear(), author.getDeathYear()),
                    () -> assertEquals(authorEntity.slug(), author.getSlug())
            );
        }

        @Test
        @DisplayName("Given null AuthorEntity should return null")
        void fromAuthorEntityToAuthorNullTest() {
            assertNull(AuthorMapper.getInstance().fromAuthorEntityToAuthor(null));
        }

        @Test
        @DisplayName("Given AuthorEntity with null name should return null")
        void fromAuthorEntityToAuthorWithNullNameTest() {
            AuthorEntity authorEntity = new AuthorEntity(1L, null, "cualquiera", null, null, 0, null, "asdfa");
            assertNull(AuthorMapper.getInstance().fromAuthorEntityToAuthor(authorEntity));
        }

        @Test
        @DisplayName("Given AuthorEntity with null slug should return null")
        void fromAuthorEntityToAuthorWithNullSlugTest() {
            AuthorEntity authorEntity = new AuthorEntity(1L, "asdfa", "cualquiera", null, null, 0, null, null);
            assertNull(AuthorMapper.getInstance().fromAuthorEntityToAuthor(authorEntity));
        }
    }

    @Nested
    class testFromAuthorToAuthorEntity {
        @Test
        @DisplayName("Given Author should return AuthorEntity")
        void fromAuthorToAuthorEntityTest() {
            Author author = new Author(1L, new Name("name"), "nationality", "biographyEs", "biographyEn", 1990, 2020, new Slug("slug"));

            AuthorEntity authorEntity = AuthorMapper.getInstance().fromAuthorToAuthorEntity(author);

            assertAll(
                    () -> assertEquals(author.getName(), authorEntity.name()),
                    () -> assertEquals(author.getNationality(), authorEntity.nationality()),
                    () -> assertEquals(author.getBiographyEs(), authorEntity.biographyEs()),
                    () -> assertEquals(author.getBiographyEn(), authorEntity.biographyEn()),
                    () -> assertEquals(author.getBirthYear(), authorEntity.birthYear()),
                    () -> assertEquals(author.getDeathYear(), authorEntity.deathYear()),
                    () -> assertEquals(author.getSlug(), authorEntity.slug())
            );
        }

        @Test
        @DisplayName("Given null Author should return null")
        void fromAuthorToAuthorEntityNullTest() {
            assertNull(AuthorMapper.getInstance().fromAuthorToAuthorEntity(null));
        }
    }

    @Nested
    class testFromAuthorToAuthorDto {
        @Test
        @DisplayName("Given Author should return AuthorDto")
        void fromAuthorToAuthorDtoTest() {
            Author author = new Author(1L, new Name("name"), "nationality", "biographyEs", "biographyEn", 1990, 2020, new Slug("slug"));

            var authorDto = AuthorMapper.getInstance().fromAuthorToAuthorDto(author);

            assertAll(
                    () -> assertEquals(author.getName(), authorDto.name()),
                    () -> assertEquals(author.getNationality(), authorDto.nationality()),
                    () -> assertEquals(author.getBiographyEs(), authorDto.biographyEs()),
                    () -> assertEquals(author.getBiographyEn(), authorDto.biographyEn()),
                    () -> assertEquals(author.getBirthYear(), authorDto.birthYear()),
                    () -> assertEquals(author.getDeathYear(), authorDto.deathYear()),
                    () -> assertEquals(author.getSlug(), authorDto.slug())
            );
        }

        @Test
        @DisplayName("Given null Author should return null")
        void fromAuthorToAuthorDtoNullTest() {
            assertNull(AuthorMapper.getInstance().fromAuthorToAuthorDto(null));
        }
    }

    @Nested
    class testFromAuthorDtoToAuthor {
        @Test
        @DisplayName("Given AuthorDto should return Author")
        void fromAuthorDtoToAuthorTest() {
            var authorDto = new AuthorDto(1L, "name", "nationality", "biographyEs", "biographyEn", 1990, 2020, "slug");
            Author author = AuthorMapper.getInstance().fromAuthorDtoToAuthor(authorDto);
            assertAll(
                    () -> assertEquals(authorDto.name(), author.getName()),
                    () -> assertEquals(authorDto.nationality(), author.getNationality()),
                    () -> assertEquals(authorDto.biographyEs(), author.getBiographyEs()),
                    () -> assertEquals(authorDto.biographyEn(), author.getBiographyEn()),
                    () -> assertEquals(authorDto.birthYear(), author.getBirthYear()),
                    () -> assertEquals(authorDto.deathYear(), author.getDeathYear()),
                    () -> assertEquals(authorDto.slug(), author.getSlug())
            );
        }

        @Test
        @DisplayName("Given null AuthorDto should return null")
        void fromAuthorDtoToAuthorNullTest() {
            assertNull(AuthorMapper.getInstance().fromAuthorDtoToAuthor(null));
        }

        @Test
        @DisplayName("Given AuthorDto with null name should throw ValidationException")
        void fromAuthorDtoToAuthorWithNullNameTest() {
            var authorDto = new AuthorDto(1L, null, "cualquiera", null, null, 0, null, "asdfa");
            assertThrows(ValidationException.class, () -> AuthorMapper.getInstance().fromAuthorDtoToAuthor(authorDto));
        }

        @Test
        @DisplayName("Given AuthorDto with empty slug should throw ValidationException")
        void fromAuthorDtoToAuthorWithEmptySlugTest() {
            var authorDto = new AuthorDto(1L, "asdfa", "cualquiera", null, null, 0, null, "");
            assertThrows(ValidationException.class, () -> AuthorMapper.getInstance().fromAuthorDtoToAuthor(authorDto));
        }
    }
}