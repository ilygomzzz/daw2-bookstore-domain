package es.javierserrano.domain.service.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record BookDto (
        Long id,
        @NotNull(message = "El ISBN es obligatorio")
        @Pattern(regexp = "\\d{13}", message = "El ISBN debe tener 13 digitos")
        String isbn,
        String titleEs,
        String titleEn,
        String synopsisEs,
        String synopsisEn,
        @NotNull(message = "El precio no puede ser nulo")
        @DecimalMin(value = "0.0", inclusive = true, message = "El precio debe ser igual o mayor a 0")
        BigDecimal basePrice,
        @NotNull
        @DecimalMin(value = "0.0", inclusive = true, message = "El descuento debe ser igual o mayor a 0")
        @DecimalMax(value = "100.0", inclusive = true, message = "El descuento no puede ser mayor a 100")
        double discountPercentage,
        BigDecimal price,
        String cover,
        @PastOrPresent(message = "La fecha de publicación no puede ser superior a la fecha actual")
        LocalDate publicationDate,
        PublisherDto publisher,
        List<AuthorDto> authors
) {
    public BookDto(
        Long id,
        String isbn,
        String titleEs,
        String titleEn,
        String synopsisEs,
        String synopsisEn,
        BigDecimal basePrice,
        double discountPercentage,
        BigDecimal price,
        String cover,
        LocalDate publicationDate,
        PublisherDto publisher,
        List<AuthorDto> authors
    ) {
        this.id = id;
        this.isbn = isbn;
        this.titleEs = titleEs;
        this.titleEn = titleEn;
        this.synopsisEs = synopsisEs;
        this.synopsisEn = synopsisEn;
        this.basePrice = basePrice;
        this.discountPercentage = discountPercentage;
        this.price = price;
        this.cover = cover;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        if (authors == null) {
            this.authors = List.of();
        } else {
            this.authors = List.copyOf(authors);
        };
    }
}
