package es.javierserrano.domain.model.book;

import es.javierserrano.domain.exception.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsbnTest {
    @Nested
    class TestConstrucutor {
        @Test
        @DisplayName("Given Isbn should return Isbn")
        void testIsbn() {
            var expected = "978-84-96811-00-9";
            var actual = new Isbn("978-84-96811-00-9");

            assertEquals(expected, actual.getIsbn());
        }

        @Test
        @DisplayName("Given blank Isbn should return ValidationException")
        void testBlankIsbn() {
            assertThrows(ValidationException.class, () -> {new Isbn(new String());});
        }

        @Test
        @DisplayName("Given null Isbn should return ValidationException")
        void testIsbnNull() {
            assertThrows(ValidationException.class, () -> {new Isbn(null);});
        }

        @Test
        @DisplayName("Given Isbn whit less than 13 digits should return ValidationException")
        void testIsbnLessThan13() {
            assertThrows(ValidationException.class, () -> {new Isbn("978-868-11-00");});
        }

        @Test
        @DisplayName("Given Isbn whit more than 13 digits should return ValidationException")
        void testIsbnMoreThan13() {
            assertThrows(ValidationException.class, () -> {new Isbn("978-84-96811-00-234-234-324");});
        }
    }

}