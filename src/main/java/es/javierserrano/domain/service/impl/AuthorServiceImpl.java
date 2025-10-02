package es.javierserrano.domain.service.impl;

import es.javierserrano.domain.exception.BusinessException;
import es.javierserrano.domain.exception.ResourceNotFoundException;
import es.javierserrano.domain.mapper.AuthorMapper;
import es.javierserrano.domain.model.Author;
import es.javierserrano.domain.repository.AuthorRepository;
import es.javierserrano.domain.repository.entity.AuthorEntity;
import es.javierserrano.domain.service.AuthorService;
import es.javierserrano.domain.service.dto.AuthorDto;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDto> getAll() {
        return authorRepository.getAll().stream()
                .map(AuthorMapper.getInstance()::fromAuthorEntityToAuthor)
                .map(AuthorMapper.getInstance()::fromAuthorToAuthorDto)
                .toList();
    }

    @Override
    public AuthorDto getBySlug(String slug) {
        return authorRepository
                .findBySlug(slug)
                .map(AuthorMapper.getInstance()::fromAuthorEntityToAuthor)
                .map(AuthorMapper.getInstance()::fromAuthorToAuthorDto)
                .orElseThrow(() -> new ResourceNotFoundException("Author with slug " + slug + " not found"));
    }

    @Override
    public AuthorDto create(AuthorDto authorDto) {
        if(authorDto == null) throw new BusinessException("Author cannot be null");
        if(authorRepository.findBySlug(authorDto.slug()).isPresent()) throw new BusinessException("Author with slug " + authorDto.slug() + " already exists");

        Author author = AuthorMapper.getInstance().fromAuthorDtoToAuthor(authorDto);
        AuthorEntity authorEntity = AuthorMapper.getInstance().fromAuthorToAuthorEntity(author);
        AuthorEntity newAuthorEntity = authorRepository.create(authorEntity);
        Author newAuthor = AuthorMapper.getInstance().fromAuthorEntityToAuthor(newAuthorEntity);
        return AuthorMapper.getInstance().fromAuthorToAuthorDto(newAuthor);
    }

    @Override
    public AuthorDto update(AuthorDto authorDto) {
        Author author = AuthorMapper.getInstance().fromAuthorDtoToAuthor(authorDto);
        AuthorEntity authorEntity = AuthorMapper.getInstance().fromAuthorToAuthorEntity(author);
        AuthorEntity newAuthorEntity = authorRepository.update(authorEntity);
        Author newAuthor = AuthorMapper.getInstance().fromAuthorEntityToAuthor(newAuthorEntity);
        return AuthorMapper.getInstance().fromAuthorToAuthorDto(newAuthor);
    }

    @Override
    public int delete(String slug) {
        return authorRepository.delete(slug);
    }
}
