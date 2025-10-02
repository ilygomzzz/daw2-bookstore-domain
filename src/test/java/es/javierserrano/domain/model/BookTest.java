package es.javierserrano.domain.model;

import es.javierserrano.domain.exception.BusinessException;
import es.javierserrano.domain.exception.ValidationException;
import es.javierserrano.domain.model.book.BasePrice;
import es.javierserrano.domain.model.book.Book;
import es.javierserrano.domain.model.book.Isbn;
import es.javierserrano.domain.model.shared.Name;
import es.javierserrano.domain.model.shared.Slug;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Nested
    class ConstructorTests {
        @Test
        @DisplayName("Given valid parameters should create Book instance")
        void constructorValidParameters() {
            Book book = new Book(
                    1L,
                    new Isbn("978-3-16-148410-0"),
                    "Título en Español",
                    "Title in English",
                    "Sinopsis en Español",
                    "Synopsis in English",
                    new BasePrice(new BigDecimal("50.00")),
                    10.0,
                    "cover.jpg",
                    LocalDate.of(2023, 1, 1),
                    new Publisher(1L, new Name("Publisher Name"), new Slug("publisher-name")),
                    List.of(new Author(1L, new Name("Author Name"), null, null, null, 1980, null, new Slug("author-name")))
            );

            assertAll(
                    () -> assertEquals("978-3-16-148410-0", book.getIsbn()),
                    () -> assertEquals(new BigDecimal("45.00").setScale(2, RoundingMode.HALF_UP), book.getPrice())
            );
        }

        @Test
        @DisplayName("Given null id should create Book instance")
        void constructorNullId() {
            Book book = new Book(
                    null,
                    new Isbn("978-3-16-148410-0"),
                    "Título en Español",
                    "Title in English",
                    "Sinopsis en Español",
                    "Synopsis in English",
                    new BasePrice(new BigDecimal("50.00")),
                    10.0,
                    "cover.jpg",
                    LocalDate.of(2023, 1, 1),
                    new Publisher(1L, new Name("Publisher Name"), new Slug("publisher-name")),
                    List.of(new Author(1L, new Name("Author Name"), null, null, null, 1980, null, new Slug("author-name")))
            );

            assertAll(
                    () -> assertEquals("978-3-16-148410-0", book.getIsbn()),
                    () -> assertEquals(new BigDecimal("45.00").setScale(2, RoundingMode.HALF_UP), book.getPrice())
            );
        }

        @ParameterizedTest(name = "{index} => basePrice={0}, excpected ValidationException")
        @DisplayName("Book with null price, 0 or less should throw ValidationException")
        @NullSource
        @CsvSource({
                "0.00",
                "-5.00"
        })
        void constructorInvalidBasePrice(BigDecimal basePrice) {
            assertThrows(ValidationException.class, () -> new Book(
                    2L,
                    new Isbn("978-3-16-148410-0"),
                    "Título en Español",
                    "Title in English",
                    "Sinopsis en Español",
                    "Synopsis in English",
                    new BasePrice(basePrice),
                    10.0,
                    "cover.jpg",
                    LocalDate.of(2023, 1, 1),
                    null,
                    null
            ));
        }
    }

    @ParameterizedTest(name = "{index} => BasePrice={0}, discountPercentage={1}, expectedPrice={2}")
    @DisplayName("Calculate final price with various discounts")
    @CsvSource({
            "100.00, 15.0, 85.00",
            "50.00, 0.0, 50.00",
            "75.00, 100.0, 0.00",
            "60.00, -10.0, 60.00"
    })
    void calculateFinalPrice(BigDecimal basePrice, double discountPercentage, BigDecimal expectedPrice) {
        Book book = new Book(
                3L,
                new Isbn("978-3-16-148410-0"),
                "Título en Español",
                "Title in English",
                "Sinopsis en Español",
                "Synopsis in English",
                new BasePrice(basePrice),
                discountPercentage,
                "cover.jpg",
                LocalDate.of(2023, 1, 1),
                null,
                null
        );

        BigDecimal expected = expectedPrice.setScale(2, RoundingMode.HALF_UP);
        assertEquals(expected, book.getPrice());
    }

    @Nested
    class AddAuthorsTest {
        @Test
        @DisplayName("Add Author to Book")
        void addAuthorToBook() {
            List<Author> authors = List.of(
                    new Author(1L, new Name("Existing Author"), null, null, null, 1900, null, new Slug("existing-author"))
            );

            Book book = new Book(
                    4L,
                    new Isbn("978-3-16-148410-0"),
                    "Título en Español",
                    "Title in English",
                    "Sinopsis en Español",
                    "Synopsis in English",
                    new BasePrice(new BigDecimal("50.00")),
                    10.0,
                    "cover.jpg",
                    LocalDate.of(2023, 1, 1),
                    null,
                    authors
            );

            Author author = new Author(1L, new Name("Existing Author 2"), null, null, null, 1900, null, new Slug("existing-author"));

            book.addAuthor(author);
            assertTrue(book.getAuthors().contains(author), "Book should contain the added author");
        }

        @Test
        @DisplayName("Add Author to Book with null Authors list")
        void addAuthorToBookWithNullAuthors() {
            Book book = new Book(
                    5L,
                    new Isbn("978-3-16-148410-0"),
                    "Título en Español",
                    "Title in English",
                    "Sinopsis en Español",
                    "Synopsis in English",
                    new BasePrice(new BigDecimal("50.00")),
                    10.0,
                    "cover.jpg",
                    LocalDate.of(2023, 1, 1),
                    null,
                    null
            );
            Author author = new Author(
                    1L,
                    new Name("Author Name"),
                    "nationality",
                    "BioEs",
                    "BioEn",
                    1980,
                    null,
                    new Slug("slug"));
            book.addAuthor(author);
            assertTrue(book.getAuthors().contains(author), "Book should contain the added author");
        }

        @Test
        @DisplayName("Add existing Author to Book should throw BusinessException")
        void addExistingAuthorToBook() {
            Author author = new Author(
                    1L,
                    new Name("Author Name"),
                    "nationality",
                    "BioEs",
                    "BioEn",
                    1980,
                    null,
                    new Slug("slug"));

            Book book = new Book(
                    6L,
                    new Isbn("978-3-16-148410-0"),
                    "Título en Español",
                    "Title in English",
                    "Sinopsis en Español",
                    "Synopsis in English",
                    new BasePrice(new BigDecimal("50.00")),
                    10.0,
                    "cover.jpg",
                    LocalDate.of(2023, 1, 1),
                    null,
                    List.of(author)
            );
            assertThrows(BusinessException.class, () -> book.addAuthor(author));
        }
    }

}