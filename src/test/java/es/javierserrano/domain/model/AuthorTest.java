package es.javierserrano.domain.model;

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
            Author author = new Author(1L,"Autor Uno", "Nacionalidad Uno", null, null, 1950, null, "prueba-1");
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
            Author author = new Author(null,"Autor Uno", "Nacionalidad Uno", null, null, 1950, null, "prueba-1");
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
    }

}