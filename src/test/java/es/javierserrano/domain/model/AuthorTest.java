package es.javierserrano.domain.model;

import es.javierserrano.domain.exception.BusinessException;
import es.javierserrano.domain.exception.ValidationException;
import es.javierserrano.domain.model.shared.Name;
import es.javierserrano.domain.model.shared.Slug;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {
    @Nested
    class ConstructorTests {
        @Test
        @DisplayName("Given valid parameters should create Author instance")
        void constructorValidParametersAuthor() {
            Author author = new Author(1L,new Name("Autor Uno"), "Nacionalidad Uno", null, null, 1950, null, new Slug("prueba-1"));
            assertAll(
                    () -> assertEquals(1L, author.getId()),
                    () -> assertEquals("Autor Uno", author.getName()),
                    () -> assertEquals("Nacionalidad Uno", author.getNationality()),
                    () -> assertNull(author.getBiographyEs()),
                    () -> assertNull(author.getBiographyEn()),
                    () -> assertEquals(1950, author.getBirthYear()),
                    () -> assertNull(author.getDeathYear()),
                    () -> assertEquals("prueba-1", author.getSlug())
            );
        }

        @Test
        @DisplayName("Given author with null ID should create Author instance")
        void constructorValidParameters() {
            Author author = new Author(null,new Name("Autor Uno"), "Nacionalidad Uno", null, null, 1950, null, new Slug("prueba-1"));
            assertAll(
                    () -> assertNull(author.getId()),
                    () -> assertEquals("Autor Uno", author.getName()),
                    () -> assertEquals("Nacionalidad Uno", author.getNationality()),
                    () -> assertNull(author.getBiographyEs()),
                    () -> assertNull(author.getBiographyEn()),
                    () -> assertEquals(1950, author.getBirthYear()),
                    () -> assertNull(author.getDeathYear()),
                    () -> assertEquals("prueba-1", author.getSlug())
            );
        }

        @Test
        @DisplayName("Author without name should throw ValidationException")
        void constructorWithoutName() {
            assertThrows(ValidationException.class, () -> new Author(1L,new Name(null), "Nacionalidad Uno", null, null, 1950, null, new Slug("prueba-1")));
        }

        @Test
        @DisplayName("Author with empty slug should throw ValidationException")
        void constructorWithEmptySlug() {
            assertThrows(ValidationException.class, () -> new Author(1L,new Name("Autor Uno"), "Nacionalidad Uno", null, null, 1950, null, new Slug("")));
        }
    }

}