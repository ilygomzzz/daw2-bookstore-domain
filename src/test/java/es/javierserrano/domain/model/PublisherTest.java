package es.javierserrano.domain.model;

import es.javierserrano.domain.exception.ValidationException;
import es.javierserrano.domain.model.shared.Name;
import es.javierserrano.domain.model.shared.Slug;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublisherTest {

    @Nested
    class TestConstructor{
        @Test
        @DisplayName("Given valid publisher should return Publisher")
        void testValidNameAndSlug() {
            Publisher publisher = new Publisher(1L, new Name("HarperCollins"), new Slug("harper-collins"));
            assertAll(
                    () -> assertEquals(1L, publisher.getId()),
                    () -> assertEquals("HarperCollins", publisher.getName()),
                    () -> assertEquals("harper-collins", publisher.getSlug())
            );
        }

        @Test
        @DisplayName("Given null id should return Publisher")
        void testNullId() {
            Publisher publisher = new Publisher(null, new Name("J.k Rowling"), new Slug("jk-rowling"));
            String expectedName = "J.k Rowling";
            assertEquals(expectedName, publisher.getName());
        }

        @Test
        @DisplayName("Given null Name should return ValidationException")
        void testNullName() {
            Publisher publisher = new Publisher(1L, null, new Slug("jk-rowling"));
            assertThrows(ValidationException.class, () -> publisher.setName(null));
        }

        @Test
        @DisplayName("Given null Slug should return ValidationException")
        void testNullSlug() {
            Publisher publisher = new Publisher(1L, new Name("Hola"), null);
            assertThrows(ValidationException.class, () -> publisher.setName(null));
        }
    }

}