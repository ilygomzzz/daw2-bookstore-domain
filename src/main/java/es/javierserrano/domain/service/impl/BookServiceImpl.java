package es.javierserrano.domain.service.impl;

import es.javierserrano.domain.exception.ResourceNotFoundException;
import es.javierserrano.domain.mapper.BookMapper;
import es.javierserrano.domain.model.Book;
import es.javierserrano.domain.model.Page;
import es.javierserrano.domain.repository.BookRepository;
import es.javierserrano.domain.repository.entity.BookEntity;
import es.javierserrano.domain.service.BookService;
import es.javierserrano.domain.service.dto.BookDto;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Page<BookDto> getAll(int page, int size) {
        Page<BookEntity> bookEntityPage = bookRepository.findAll(page, size);
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
       return bookRepository
               .findByIsbn(isbn)
               .map(BookMapper.getInstance()::fromBookEntityToBook)
               .map(BookMapper.getInstance()::fromBookToBookDto);
    }

    @Override
    public BookDto create(BookDto bookDto) {
        Book book =  BookMapper.getInstance().fromBookDtoToBook(bookDto);
        BookEntity bookEntity = BookMapper.getInstance().fromBookToBookEntity(book);
        BookEntity newBookEntity = bookRepository.create(bookEntity);
        Book newBook =  BookMapper.getInstance().fromBookEntityToBook(newBookEntity);
        return BookMapper.getInstance().fromBookToBookDto(newBook);
    }

    @Override
    public BookDto update(BookDto bookDto) {
        Book book =  BookMapper.getInstance().fromBookDtoToBook(bookDto);
        BookEntity bookEntity = BookMapper.getInstance().fromBookToBookEntity(book);
        BookEntity newBookEntity = bookRepository.update(bookEntity);
        Book newBook =  BookMapper.getInstance().fromBookEntityToBook(newBookEntity);
        return BookMapper.getInstance().fromBookToBookDto(newBook);
    }

    @Override
    public int delete(String isbn) {
        return bookRepository.delete(isbn);
    }
}
