package es.javierserrano.domain.mapper;

import es.javierserrano.domain.exception.BusinessException;
import es.javierserrano.domain.exception.ValidationException;
import es.javierserrano.domain.model.Author;
import es.javierserrano.domain.model.shared.Name;
import es.javierserrano.domain.model.shared.Slug;
import es.javierserrano.domain.repository.entity.AuthorEntity;
import es.javierserrano.domain.service.dto.AuthorDto;

public class AuthorMapper {
    private static AuthorMapper instance;

    private AuthorMapper() {}

    public static AuthorMapper getInstance() {
        if (instance == null) {
            instance = new AuthorMapper();
        }
        return instance;
    }

    public Author fromAuthorEntityToAuthor(AuthorEntity authorEntity) {
        if (authorEntity == null) {
            return null;
        }
        try {
            return new Author(
                    authorEntity.id(),
                    new Name(authorEntity.name()),
                    authorEntity.nationality(),
                    authorEntity.biographyEs(),
                    authorEntity.biographyEn(),
                    authorEntity.birthYear(),
                    authorEntity.deathYear(),
                    new Slug(authorEntity.slug())
            );
        } catch (ValidationException e) {
            return null;
        }
    }

    public AuthorEntity fromAuthorToAuthorEntity(Author author) {
        if (author == null) {
            return null;
        }

        return new AuthorEntity(
                author.getId(),
                author.getName(),
                author.getNationality(),
                author.getBiographyEs(),
                author.getBiographyEn(),
                author.getBirthYear(),
                author.getDeathYear(),
                author.getSlug()
        );
    }

    public AuthorDto fromAuthorToAuthorDto(Author author) {
        if (author == null) {
            return null;
        }

        return new AuthorDto(
                author.getId(),
                author.getName(),
                author.getNationality(),
                author.getBiographyEs(),
                author.getBiographyEn(),
                author.getBirthYear(),
                author.getDeathYear(),
                author.getSlug()
        );
    }

    public Author fromAuthorDtoToAuthor(AuthorDto authorDto) {
        if (authorDto == null) {
            return null;
        }

        return new Author(
                authorDto.id(),
                new Name(authorDto.name()),
                authorDto.nationality(),
                authorDto.biographyEs(),
                authorDto.biographyEn(),
                authorDto.birthYear(),
                authorDto.deathYear(),
                new Slug(authorDto.slug())
        );
    }
}
