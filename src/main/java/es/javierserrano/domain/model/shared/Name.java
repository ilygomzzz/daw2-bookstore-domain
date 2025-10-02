package es.javierserrano.domain.model.shared;

import es.javierserrano.domain.exception.BusinessException;
import es.javierserrano.domain.exception.ValidationException;

import java.util.Objects;

public final class Name {
    private final String name;

    public Name(String name) {
        if(name == null || name.isBlank() || name.trim().isEmpty()){
            throw new ValidationException("El nombre no puede estar vacio");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Name name)) return false;
        return this.name.equals(name.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
