package es.javierserrano.domain.service.dto;

public record AuthorDto (
        Long id,
        String name,
        String nationality,
        String biographyEs,
        String biographyEn,
        int birthYear,
        Integer deathYear,
        String slug
) {
}