package es.javierserrano.domain.model.book;

import es.javierserrano.domain.exception.ValidationException;

import java.util.Objects;

public final class Isbn {
    private final String isbn;

    public Isbn(String isbn) {
        if(isbn ==null || isbn.isBlank()){
            throw new ValidationException("El isbn no puede estar vacio o ser nulo");
        }

        if(isbn.chars().filter(Character::isDigit).count() != 13){
            throw new ValidationException("El isbn debe de estar compuesto por 13 dígitos");
        }

        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Isbn name)) return false;
        return isbn.equals(name.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return isbn;
    }
}
