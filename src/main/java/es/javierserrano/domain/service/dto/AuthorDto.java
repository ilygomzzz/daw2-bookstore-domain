package es.javierserrano.domain.service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

public record AuthorDto (
        Long id,
        @NotNull(message = "El nombre es obligatorio")
        String name,
        String nationality,
        String biographyEs,
        String biographyEn,
        int birthYear,
        Integer deathYear,
        @NotNull(message = "El slug es obligatorio")
        @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$", message = "El slug solo puede contener letras minúsculas, números y guiones")
        String slug
) {
    public AuthorDto(
        Long id,
        String name,
        String nationality,
        String biographyEs,
        String biographyEn,
        int birthYear,
        Integer deathYear,
        String slug
    ) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.biographyEs = biographyEs;
        this.biographyEn = biographyEn;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.slug = slug;
    }
}