package es.javierserrano.domain.service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PublisherDto(
        Long id,
        @NotNull(message = "El nombre es obligatorio")
        String name,
        @NotNull(message = "El slug es obligatorio")
        @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$", message = "El slug solo puede contener letras minúsculas, números y guiones")
        String slug
) {
    public PublisherDto(
            Long id,
            String name,
            String slug
    ) {
        this.id = id;
        this.name = name;
        this.slug = slug;
    }
}
