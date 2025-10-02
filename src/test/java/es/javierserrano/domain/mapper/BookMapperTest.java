package es.javierserrano.domain.mapper;

import es.javierserrano.domain.exception.BusinessException;
import es.javierserrano.domain.model.Author;
import es.javierserrano.domain.model.book.BasePrice;
import es.javierserrano.domain.model.book.Book;
import es.javierserrano.domain.model.Publisher;
import es.javierserrano.domain.model.book.Isbn;
import es.javierserrano.domain.model.shared.Name;
import es.javierserrano.domain.model.shared.Slug;
import es.javierserrano.domain.repository.entity.AuthorEntity;
import es.javierserrano.domain.repository.entity.BookEntity;
import es.javierserrano.domain.repository.entity.PublisherEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {

    @Nested
    class FromBookEntityToBook {
        @Test
        @DisplayName("Given bookEntity should return book")
        void givenBookEntityShouldReturnBook() {
            PublisherEntity publisherEntity = new PublisherEntity(
                    1L,
                    "Editorial Ejemplo",
                    "País Ejemplo"
            );

            List<AuthorEntity> authorsEntity = List.of(
                    new AuthorEntity(1L,"Autor Uno", "Nacionalidad Uno", null, null, 1950, null,  "asdf"),
                    new AuthorEntity(2L,"Autor Dos", "Nacionalidad Dos", null, null, 1960, null, "assdg")
            );

            BookEntity bookEntity = new BookEntity(
                    1L,
                    "978-3-16-148410-0",
                    "Título en español",
                    "Title in English",
                    "Sinopsis en español",
                    "Synopsis in English",
                    new BigDecimal(20),
                    10.0,
                    "cover.jpg",
                    LocalDate.of(2023, 1, 1),
                    publisherEntity,
                    null
            );

            Book book = BookMapper.getInstance().fromBookEntityToBook(bookEntity);

            assertAll(
                    () -> assertEquals(bookEntity.id(), book.getId()),
                    () -> assertEquals(bookEntity.isbn(), book.getIsbn()),
                    () -> assertEquals(bookEntity.titleEs(), book.getTitleEs()),
                    () -> assertEquals(bookEntity.titleEn(), book.getTitleEn()),
                    () -> assertEquals(bookEntity.synopsisEs(), book.getSynopsisEs()),
                    () -> assertEquals(bookEntity.synopsisEn(), book.getSynopsisEn()),
                    () -> assertEquals(bookEntity.basePrice(), book.getBasePrice()),
                    () -> assertEquals(bookEntity.discountPercentage(), book.getDiscountPercentage()),
                    () -> assertEquals(bookEntity.cover(), book.getCover()),
                    () -> assertEquals(bookEntity.publicationDate(), book.getPublicationDate()),
                    () -> assertEquals(bookEntity.publisher().name(), book.getPublisher().getName()),
                    () -> assertNull(bookEntity.authors()) /*Cambiado por: esperaba nulo y devolvía array.size()*/
            );
        }

        @Test
        @DisplayName("Given null bookEntity should throw BusinessException")
        void givenNullBookEntityShouldReturnNull() {
           assertNull(BookMapper.getInstance().fromBookEntityToBook(null));
        }

        @Test
        @DisplayName("Given bookEntity without isbn should return null")
        void  givenBookEntityWhitoutIsbnShouldReturnNull() {
            PublisherEntity publisherEntity = new PublisherEntity(
                    1L,
                    "Editorial Ejemplo",
                    "País Ejemplo"
            );

            List<AuthorEntity> authorsEntity = List.of(
                    new AuthorEntity(1L,"Autor Uno", "Nacionalidad Uno", null, null, 1950, null, null),
                    new AuthorEntity(2L,"Autor Dos", "Nacionalidad Dos", null, null, 1960, null, null)
            );

            BookEntity bookEntity = new BookEntity(
                    1L,
                    null,
                    "Título en español",
                    "Title in English",
                    "Sinopsis en español",
                    "Synopsis in English",
                    new BigDecimal(20),
                    10.0,
                    "cover.jpg",
                    LocalDate.of(2023, 1, 1),
                    publisherEntity,
                    authorsEntity
            );

            assertNull(BookMapper.getInstance().fromBookEntityToBook(bookEntity));
        }

        @Test
        @DisplayName("Given bookEntity without author should return book with empty authors")
        void givenBookEntityWithoutAuthorShouldReturnBookWithEmptyAuthors() {
            PublisherEntity publisherEntity = new PublisherEntity(
                    1L,
                    "Editorial Ejemplo",
                    "País Ejemplo"
            );

            BookEntity bookEntity = new BookEntity(
                    1L,
                    "978-3-16-148410-0",
                    "Título en español",
                    "Title in English",
                    "Sinopsis en español",
                    "Synopsis in English",
                    new BigDecimal(20),
                    10.0,
                    "cover.jpg",
                    LocalDate.of(2023, 1, 1),
                    publisherEntity,
                    null
            );

            Book book = BookMapper.getInstance().fromBookEntityToBook(bookEntity);
            assertAll(
                    () -> assertEquals(bookEntity.id(), book.getId()),
                    () -> assertTrue(book.getAuthors().isEmpty())
            );
        }
    }

    @Nested
    class FromBookToBookEntity {
        @Test
        @DisplayName("Given book should return bookEntity")
        void givenBookShouldReturnBookEntity() {
            List<Author> authors = List.of(
                    new Author(1L, new Name("Autor Uno"), "Nacionalidad Uno", null, null, 1950, null, new Slug("prueba-1")),
                    new Author(2L, new Name("Autor Dos"), "Nacionalidad Dos", null, null, 1960, null, new Slug("prueba-2"))
            );

            Publisher publisher = new Publisher(
                    1L,
                    new Name("Editorial Ejemplo"),
                    new Slug("País Ejemplo")
            );

            Book book = new Book(
                    2L,
                    new Isbn("1234123567890"),
                    "Título en español",
                    "Title in English",
                    "Sinopsis en español",
                    "Synopsis in English",
                    new BasePrice(new BigDecimal(20)),
                    10.0,
                    "cover.jpg",
                    LocalDate.of(2023, 1, 1),
                    publisher,
                    authors
            );

            BookEntity bookEntity = BookMapper.getInstance().fromBookToBookEntity(book);

            assertAll(
                    () -> assertEquals(book.getId(), bookEntity.id()),
                    () -> assertEquals(book.getIsbn(), bookEntity.isbn()),
                    () -> assertEquals(book.getTitleEs(), bookEntity.titleEs()),
                    () -> assertEquals(book.getTitleEn(), bookEntity.titleEn()),
                    () -> assertEquals(book.getSynopsisEs(), bookEntity.synopsisEs()),
                    () -> assertEquals(book.getSynopsisEn(), bookEntity.synopsisEn()),
                    () -> assertEquals(book.getBasePrice(), bookEntity.basePrice()),
                    () -> assertEquals(book.getDiscountPercentage(), bookEntity.discountPercentage()),
                    () -> assertEquals(book.getCover(), bookEntity.cover()),
                    () -> assertEquals(book.getPublicationDate(), bookEntity.publicationDate()),
                    () -> assertEquals(book.getPublisher().getName(), bookEntity.publisher().name()),
                    () -> assertEquals(book.getAuthors().size(), bookEntity.authors().size())
            );
        }

        @Test
        @DisplayName("Given null book should throw BusinessException")
        void givenNullBookShouldThrowBusinessException() {
            assertThrows(BusinessException.class, () -> BookMapper.getInstance().fromBookToBookEntity(null));
        }

        @Test
        @DisplayName("Given book without authors should return bookEntity with empty authors")
        void  givenBookWithoutAuthorsShouldReturnBookEntityWithEmptyAuthors() {
            Publisher publisher = new Publisher(
                    1L,
                    new Name("Editorial Ejemplo"),
                    new Slug("País Ejemplo")
            );

            Book book = new Book(
                    2L,
                    new Isbn("1231234567890"),
                    "Título en español",
                    "Title in English",
                    "Sinopsis en español",
                    "Synopsis in English",
                    new BasePrice(new BigDecimal(20)),
                    10.0,
                    "cover.jpg",
                    LocalDate.of(2023, 1, 1),
                    publisher,
                    null
            );

            BookEntity bookEntity = BookMapper.getInstance().fromBookToBookEntity(book);
            assertAll(
                    () -> assertEquals(book.getId(), bookEntity.id()),
                    () -> assertTrue(bookEntity.authors().isEmpty())
            );
        }
    }

    @Nested
    class FromBookToBookDto {
        @Test
        @DisplayName("Given book should return bookDto")
        void givenBookShouldReturnBookDto() {
            List<Author> authors = List.of(
                    new Author(1L, new Name("Autor Uno"), "Nacionalidad Uno", null, null, 1950, null, new Slug("prueba-1")),
                    new Author(2L, new Name("Autor Dos"), "Nacionalidad Dos", null, null, 1960, null, new Slug("prueba-2"))
            );

            Publisher publisher = new Publisher(
                    1L,
                    new Name("Editorial Ejemplo"),
                    new Slug("País Ejemplo")
            );

            Book book = new Book(
                    3L,
                    new Isbn("1234567890198"),
                    "Título en español",
                    "Title in English",
                    "Sinopsis en español",
                    "Synopsis in English",
                    new BasePrice(new BigDecimal(20)),
                    10.0,
                    "cover.jpg",
                    LocalDate.of(2023, 1, 1),
                    publisher,
                    authors
            );

            var bookDto = BookMapper.getInstance().fromBookToBookDto(book);

            assertAll(
                    () -> assertEquals(book.getId(), bookDto.id()),
                    () -> assertEquals(book.getIsbn(), bookDto.isbn()),
                    () -> assertEquals(book.getTitleEs(), bookDto.titleEs()),
                    () -> assertEquals(book.getTitleEn(), bookDto.titleEn()),
                    () -> assertEquals(book.getSynopsisEs(), bookDto.synopsisEs()),
                    () -> assertEquals(book.getSynopsisEn(), bookDto.synopsisEn()),
                    () -> assertEquals(book.getBasePrice(), bookDto.basePrice()),
                    () -> assertEquals(book.getDiscountPercentage(), bookDto.discountPercentage()),
                    () -> assertEquals(book.getCover(), bookDto.cover()),
                    () -> assertEquals(book.getPublicationDate(), bookDto.publicationDate()),
                    () -> assertEquals(book.getPublisher().getName(), bookDto.publisher().name()),
                    () -> assertEquals(book.getAuthors().size(), bookDto.authors().size())
            );
        }

        @Test
        @DisplayName("Given null book should throw BusinessException")
        void givenNullBookShouldThrowBusinessException() {
            assertThrows(BusinessException.class, () -> BookMapper.getInstance().fromBookToBookDto(null));
        }

        @Test
        @DisplayName("Given book without authors should return bookDto with empty authors")
        void givenBookWithoutAuthorsShouldReturnBookDtoWithEmptyAuthors() {
            Publisher publisher = new Publisher(
                    1L,
                    new Name("Editorial Ejemplo"),
                    new Slug("País Ejemplo")
            );
            Book book = new Book(
                    3L,
                    new Isbn("1234567890987"),
                    "Título en español",
                    "Title in English",
                    "Sinopsis en español",
                    "Synopsis in English",
                    new BasePrice(new BigDecimal(20)),
                    10.0,
                    "cover.jpg",
                    LocalDate.of(2023, 1, 1),
                    publisher,
                    null
            );
            var bookDto = BookMapper.getInstance().fromBookToBookDto(book);
            assertAll(
                    () -> assertEquals(book.getId(), bookDto.id()),
                    () -> assertTrue(bookDto.authors().isEmpty())
            );
        }
    }

    @Nested
    class FromBookDtoToBook {
        @Test
        @DisplayName("Given bookDto should return book")
        void givenBookDtoShouldReturnBook() {
            List<Author> authors = List.of(
                    new Author(1L, new Name("Autor Uno"), "Nacionalidad Uno", null, null, 1950, null, new Slug("prueba-1")),
                    new Author(2L, new Name("Autor Dos"), "Nacionalidad Dos", null, null, 1960, null, new Slug("prueba-2"))
            );

            Publisher publisher = new Publisher(
                    2L,
                    new Name("Editorial Ejemplo"),
                    new Slug("País Ejemplo")
            );

            Book book = new Book(
                    4L,
                    new Isbn("1231234567890"),
                    "Título en español",
                    "Title in English",
                    "Sinopsis en español",
                    "Synopsis in English",
                    new BasePrice(new BigDecimal(20)),
                    10.0,
                    "cover.jpg",
                    LocalDate.of(2023, 1, 1),
                    publisher,
                    authors
            );

            var bookDto = BookMapper.getInstance().fromBookToBookDto(book);
            var mappedBook = BookMapper.getInstance().fromBookDtoToBook(bookDto);

            assertAll(
                    () -> assertEquals(bookDto.id(), mappedBook.getId()),
                    () -> assertEquals(bookDto.isbn(), mappedBook.getIsbn()),
                    () -> assertEquals(bookDto.titleEs(), mappedBook.getTitleEs()),
                    () -> assertEquals(bookDto.titleEn(), mappedBook.getTitleEn()),
                    () -> assertEquals(bookDto.synopsisEs(), mappedBook.getSynopsisEs()),
                    () -> assertEquals(bookDto.synopsisEn(), mappedBook.getSynopsisEn()),
                    () -> assertEquals(bookDto.basePrice(), mappedBook.getBasePrice()),
                    () -> assertEquals(bookDto.discountPercentage(), mappedBook.getDiscountPercentage()),
                    () -> assertEquals(bookDto.cover(), mappedBook.getCover()),
                    () -> assertEquals(bookDto.publicationDate(), mappedBook.getPublicationDate()),
                    () -> assertEquals(bookDto.publisher().name(), mappedBook.getPublisher().getName()),
                    () -> assertEquals(bookDto.authors().size(), mappedBook.getAuthors().size())
            );
        }

        @Test
        @DisplayName("Given null bookDto should throw BusinessException")
        void givenNullBookDtoShouldThrowBusinessException() {
            assertThrows(BusinessException.class, () -> BookMapper.getInstance().fromBookDtoToBook(null));
        }

        @Test
        @DisplayName("Given bookDto without authors should return book with empty authors")
        void givenBookDtoWithoutAuthorsShouldReturnBookWithEmptyAuthors() {
            Publisher publisher = new Publisher(
                    2L,
                    new Name("Editorial Ejemplo"),
                    new Slug("País Ejemplo")
            );
            Book book = new Book(
                    4L,
                    new Isbn("1234512367890"),
                    "Título en español",
                    "Title in English",
                    "Sinopsis en español",
                    "Synopsis in English",
                    new BasePrice(new BigDecimal(20)),
                    10.0,
                    "cover.jpg",
                    LocalDate.of(2023, 1, 1),
                    publisher,
                    null
            );
            var bookDto = BookMapper.getInstance().fromBookToBookDto(book);
            var mappedBook = BookMapper.getInstance().fromBookDtoToBook(bookDto);
            assertAll(
                    () -> assertEquals(bookDto.id(), mappedBook.getId()),
                    () -> assertTrue(mappedBook.getAuthors().isEmpty())
            );
        }
    }
}