package es.javierserrano.domain.model.shared;

import es.javierserrano.domain.exception.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {
    @Nested
    class constructorTests {
        @Test
        @DisplayName("Given Name should return name")
        void testName() {
            String expected = "name";
            Name name = new Name(expected);

            assertEquals(expected,name.getName());
        }

        @Test
        @DisplayName("Given null to Name should return ValidationException")
        void testNullName() {
            assertThrows(ValidationException.class, () -> new Name(null));
        }

        @Test
        @DisplayName("Given empty name should return ValidationException")
        void testEmptyName() {
            assertThrows(ValidationException.class, () -> new Name(""));
        }
    }

}