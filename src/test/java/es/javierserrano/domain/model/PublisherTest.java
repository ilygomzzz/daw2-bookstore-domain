package es.javierserrano.domain.model;

import es.javierserrano.domain.exception.ValidationException;
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
            Publisher publisher = new Publisher(1L, "HarperCollins", "harper-collins");
            assertAll(
                    () -> assertEquals(1L, publisher.getId()),
                    () -> assertEquals("HarperCollins", publisher.getName()),
                    () -> assertEquals("harper-collins", publisher.getSlug())
            );
        }

        @Test
        @DisplayName("Given null id should return Publisher")
        void testNullId() {
            Publisher publisher = new Publisher(null, "J.k Rowling", "jk-rowling");
            String expectedName = "J.k Rowling";
            assertEquals(expectedName, publisher.getName());
        }
    }

}