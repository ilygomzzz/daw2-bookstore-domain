package es.javierserrano.domain.model;

import es.javierserrano.domain.model.book.BasePrice;
import es.javierserrano.domain.model.book.Book;
import es.javierserrano.domain.model.book.Isbn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PageTest {

    @Nested
    @DisplayName("Tests for DataValidation")
    class DataValidationTests {
        @Test
        @DisplayName("Data smaller than Page Size should return data")
        void testValidationDataSize() {
            List<Book> data = List.of(
                    new Book(
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
                            null
                    )
            );

            int pageSize = 5;
            Page<Book> bookPage = new Page<>(data,1,pageSize, data.size());
            assertEquals(bookPage.data(), data);
        }

        @Test
        @DisplayName("Data size equals to page size should return data")
        void testValidationDataSizeEqualsPageSize() {
            List<Book> data = List.of(
                    new Book(
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
                    )
            );

            int pageSize = 1;
            Page<Book> bookPage = new Page<>(data,1,pageSize, data.size());
            assertEquals(bookPage.data(), data);
        }

        @Test
        @DisplayName("Data size greater than page size should throw RuntimeException")
        void testValidationDataSizeGreaterThanPageSizeShouldThrowBusinessException() {
            List<Book> data = List.of(
                    new Book(
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
                            null
                    ),
                    new Book(
                            2L,
                            new Isbn("978-3-16-148412-0"),
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
                    )
            );

            int pageSize = 1;
            assertThrows(RuntimeException.class, () -> new Page<>(data,1,pageSize, data.size()));
        }
    }

    @Nested
    @DisplayName("Tests for PageNumberValidation")
    class PageNumberValidationTests {
        @Test
        @DisplayName("Page number greater than 0 should return pageNumber")
        void testValidationPageNumberGreaterThan0ShouldReturnPageNumber() {
            List<Book> data = List.of(
                    new Book(
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
                            null,
                            null
                    )
            );

            int pageSize = 1;
            Page<Book> bookPage = new Page<>(data,1,pageSize, data.size());
            assertEquals(bookPage.pageNumber(), 1);
        }

        @Test
        @DisplayName("Page number smaller than 1 should throw RuntimeException")
        void testValidationPageNumberSmallerThan0ShouldThrowBusinessException() {
            List<Book> data = List.of(
                    new Book(
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
                            null,
                            null
                    )
            );

            int pageSize = 1;
            assertThrows(RuntimeException.class, () -> new Page<>(data,0,pageSize, data.size()));
        }
    }

    @Nested
    @DisplayName("Tests for validatePageSize")
    class PageSizeValidationTests {
        @Test
        @DisplayName("Page size greater than 0 should return pageNumber")
        void testValidationPageNumberGreaterThan0ShouldReturnPageNumber() {
            List<Book> data = List.of(
                    new Book(
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
                            null,
                            null
                    )
            );

            int pageSize = 1;
            Page<Book> bookPage = new Page<>(data,1,pageSize, data.size());
            assertEquals(bookPage.pageNumber(), 1);
        }

        @Test
        @DisplayName("Page size smaller than 1 should throw RuntimeException")
        void testValidationPageNumberSmallerThan0ShouldThrowBusinessException() {
            List<Book> data = List.of(
                    new Book(
                            2L,
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
                    )
            );

            int pageSize = 0;
            assertThrows(RuntimeException.class, () -> new Page<>(data,1,pageSize, data.size()));
        }
    }

    @ParameterizedTest(name="{index} totalElements={0}, pageSize={1}, expectedTotalPages={2}")
    @DisplayName("TotalPagesTest")
    @CsvSource({
            "1,1,1",
            "10,10,1",
            "20,5,4"
    })
    void testTotalPages(int totalElements, int pageSize, int expectedTotalPages) {
        List<Book> data = List.of();
        Page<Book> bookPage = new Page<>(data,1,pageSize, totalElements);
        assertEquals(expectedTotalPages, bookPage.totalPages());
    }

}