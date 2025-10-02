package es.javierserrano.domain.model.shared;

import es.javierserrano.domain.exception.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlugTest {
    @Nested
    class testConstructor{
        @Test
        @DisplayName("Given valid Slug shluld return Slug")
        void testValidSlug() {
            String expectedSlug = "JK-Rowling";
            Slug slug = new Slug(expectedSlug);
            assertEquals(expectedSlug, slug.getSlug());
        }

        @Test
        @DisplayName("Given null slug should return ValidationException")
        void testNullSlug() {
            assertThrows(ValidationException.class, () -> new Slug(null));
        }

        @Test
        @DisplayName("Given empty slug should return ValidationException")
        void testEmptySlug() {
            assertThrows(ValidationException.class, () -> new Slug(null));
        }
    }

}