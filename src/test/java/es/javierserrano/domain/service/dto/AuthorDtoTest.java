package es.javierserrano.domain.service.dto;

import es.javierserrano.domain.exception.ValidationException;
import es.javierserrano.domain.sping_validator.DtoValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class AuthorDtoTest {

    @ParameterizedTest
    @DisplayName("Create authorDto with invalid data should throw ValidationException")
    @CsvSource({
            ", 'valid-slug',",
            ",,",
            "'Valid Name', ''",
            "'Valid Name', '!invalid-slug'"
    })
    void createAuthorDtoWithInvalidDataShouldThrowValidationException(String name, String slug) {
        AuthorDto authorDto = new AuthorDto(
                1L,
                name,
                "Some National",
                "Some Biography ES",
                "Some Biography EN",
                1950,
                null,
                slug
        );
        assertThrows(ValidationException.class, () -> DtoValidator.validate(authorDto));
    }

}