package es.javierserrano.domain.service.impl;

import es.javierserrano.domain.exception.BusinessException;
import es.javierserrano.domain.exception.ResourceNotFoundException;
import es.javierserrano.domain.mapper.AuthorMapper;
import es.javierserrano.domain.mapper.BookMapper;
import es.javierserrano.domain.mapper.PublisherMapper;
import es.javierserrano.domain.model.Book;
import es.javierserrano.domain.model.Page;
import es.javierserrano.domain.repository.AuthorRepository;
import es.javierserrano.domain.repository.BookRepository;
import es.javierserrano.domain.repository.PublisherRepository;
import es.javierserrano.domain.repository.entity.AuthorEntity;
import es.javierserrano.domain.repository.entity.BookEntity;
import es.javierserrano.domain.repository.entity.PublisherEntity;
import es.javierserrano.domain.service.BookService;
import es.javierserrano.domain.service.dto.BookDto;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, PublisherRepository publisherRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Page<BookDto> getAll(int page, int size) {
        Page<BookEntity> bookEntityPage =  bookRepository
                .findAll(page, size);
        List<BookDto> itemsDto = bookEntityPage.data()
                .stream()
                .map(BookMapper.getInstance()::fromBookEntityToBook)
                .map(BookMapper.getInstance()::fromBookToBookDto)
                .toList();
        return new Page<>(
                itemsDto,
                bookEntityPage.pageNumber(),
                bookEntityPage.pageSize(),
                bookEntityPage.totalElements()
        );
    }

    @Override
    public BookDto getByIsbn(String isbn) {
        return bookRepository
                .findByIsbn(isbn)
                .map(BookMapper.getInstance()::fromBookEntityToBook)
                .map(BookMapper.getInstance()::fromBookToBookDto)
                .orElseThrow(() -> new ResourceNotFoundException("Book with isbn " + isbn + " not found"));
    }

    @Override
    public Optional<BookDto> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .map(BookMapper.getInstance()::fromBookEntityToBook)
                .map(BookMapper.getInstance()::fromBookToBookDto);
    }



    @Override
    @Transactional
    public BookDto create(BookDto bookDto) {
        if (findByIsbn(bookDto.isbn()).isPresent()) {
            throw new BusinessException("Book with isbn " + bookDto.isbn() + " already exists");
        }

        BookEntity newBookEntity = buildBookEntityFromBookDto(bookDto);

        return BookMapper.getInstance().fromBookToBookDto(
                BookMapper.getInstance().fromBookEntityToBook(
                        bookRepository.save(newBookEntity)
                )
        );
    }


    @Override
    @Transactional
    public BookDto update(BookDto bookDto) {
        bookRepository.findById(bookDto.id()).orElseThrow(
                () -> new ResourceNotFoundException("Book with id " + bookDto.id() + " not found")
        );

        // Comprobar duplicidad de ISBN (salvo el propio libro)
        bookRepository.findByIsbn(bookDto.isbn())
                .filter(b -> !b.id().equals(bookDto.id()))
                .ifPresent(b -> {
                    throw new BusinessException("Another book with ISBN " + bookDto.isbn() + " already exists");
                });

        BookEntity newBookEntity = buildBookEntityFromBookDto(bookDto);

        return BookMapper.getInstance().fromBookToBookDto(
                BookMapper.getInstance().fromBookEntityToBook(
                        bookRepository.save(newBookEntity)
                )
        );
    }


    private BookEntity buildBookEntityFromBookDto(BookDto bookDto) {
        PublisherEntity publisherEntity = (bookDto.publisher() != null)
                ? publisherRepository.findById(bookDto.publisher().id())
                .orElseThrow(() -> new ResourceNotFoundException("Publisher with id " + bookDto.publisher().id() + " does not exist"))
                : null;

        List<AuthorEntity> authorEntities = (bookDto.authors() != null)
                ? bookDto.authors().stream()
                .map(authorDto -> authorRepository.findById(authorDto.id())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Author with id " + authorDto.id() + " does not exist")))
                .toList()
                : List.of();

        Book newBook = BookMapper.getInstance().fromBookDtoToBook(bookDto);
        newBook.setPublisher(PublisherMapper.getInstance().fromPublisherEntityToPublisher(publisherEntity));
        newBook.setAuthors(authorEntities.stream()
                .map(AuthorMapper.getInstance()::fromAuthorEntityToAuthor)
                .toList());

        return BookMapper.getInstance().fromBookToBookEntity(newBook);
    }

    @Override
    @Transactional
    public void deleteByIsbn(String isbn) {
        Optional<BookDto> existingBookDto = findByIsbn(isbn);

        if (existingBookDto.isEmpty()) {
            throw new BusinessException("Book with isbn " + isbn + " does not exist");
        }

        bookRepository.deleteByIsbn(isbn);
    }
}
