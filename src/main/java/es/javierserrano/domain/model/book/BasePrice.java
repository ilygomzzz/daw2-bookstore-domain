package es.javierserrano.domain.model.book;

import es.javierserrano.domain.exception.ValidationException;

import java.math.BigDecimal;
import java.util.Objects;

public final class BasePrice {
    private final BigDecimal basePrice;

    public BasePrice(BigDecimal basePrice) {
        if(basePrice ==null || basePrice.compareTo(BigDecimal.ZERO)<=0){
            throw new ValidationException("El precio base debe ser mayor que cero");
        }
        this.basePrice = basePrice;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BasePrice price)) return false;
        return basePrice.equals(price.basePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basePrice);
    }

    @Override
    public String toString() {
        return basePrice.toString();
    }
}
