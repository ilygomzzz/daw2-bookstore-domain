package es.javierserrano.domain.service.dto;

import es.javierserrano.domain.exception.ValidationException;
import es.javierserrano.domain.sping_validator.DtoValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BookDtoTest {

    static Stream<Arguments> provideInvalidArguments() {
        return Stream.of(
                Arguments.of("", new BigDecimal("10.00"), "5.0", LocalDate.now()),
                Arguments.of("123", new BigDecimal("10.00"), "5.0", LocalDate.now()),
                Arguments.of("12345678901234", new BigDecimal("10.00"), "5.0", LocalDate.now()),
                Arguments.of("ABCDFGERTYTGG", new BigDecimal("10.00"), "5.0", LocalDate.now()),
                Arguments.of("9999999999999", null, "5.0", LocalDate.now()),
                Arguments.of("9999999999999", new BigDecimal("-10.00"), "5.0", LocalDate.now()),
                Arguments.of("9999999999999", new BigDecimal("10.00"), "-5.0", LocalDate.now()),
                Arguments.of("9999999999999", new BigDecimal("10.00"), "105.0", LocalDate.now()),
                Arguments.of("9999999999999", new BigDecimal("10.00"), null, LocalDate.now()),
                Arguments.of("9999999999999", new BigDecimal("10.00"), "5.0", LocalDate.now().plusDays(1))
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidArguments")
    void testBookDtoValidation(String isbn, BigDecimal basePrice, Double discountPercentage, LocalDate publicationDate) {
        BookDto invalidBookDto = new BookDto(
                1L,
                isbn,
                "Sample Title ES",
                "Sample Title EN",
                "Sample Synopsis ES",
                "Sample Synopsis EN",
                basePrice,
                discountPercentage,
                null,
                "Sample Cover",
                publicationDate,
                null,
                null
        );

        assertThrows(ValidationException.class, () -> DtoValidator.validate(invalidBookDto));
    }
}