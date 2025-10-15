package es.javierserrano.domain.service.dto;

import es.javierserrano.domain.exception.ValidationException;
import es.javierserrano.domain.sping_validator.DtoValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PublisherDtoTest {
    @ParameterizedTest
    @DisplayName("Create publisherDto with invalid data should throw ValidationException")
    @CsvSource({
            "1,, 'valid-slug'",
            "1, 'Valid Name', ''",
            "1, '', ''",
            "1, Valid Name, invalid slug",
            "1, Valid Name, 'invalid_slug!'"
    })
    void createPublisherDto_WithNullName_ShouldThrowException(Long id, String name, String slug) {
        PublisherDto publisherDto = new PublisherDto(id, name, slug);
        assertThrows(ValidationException.class, () -> DtoValidator.validate(publisherDto));

    }
}