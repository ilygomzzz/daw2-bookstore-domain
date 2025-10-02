package es.javierserrano.domain.model.shared;


import es.javierserrano.domain.exception.BusinessException;
import es.javierserrano.domain.exception.ValidationException;

import java.util.Objects;

public final class Slug {
    private final String slug;

    public Slug(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException("Slug inválido");
        }
        this.slug = value;
    }

    public String getSlug() {
        return slug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Slug slug)) return false;
        return this.slug.equals(slug.slug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slug);
    }

    @Override
    public String toString() {
        return slug;
    }
}