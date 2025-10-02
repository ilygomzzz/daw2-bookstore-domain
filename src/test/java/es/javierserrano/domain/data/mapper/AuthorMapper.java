package es.javierserrano.domain.data.mapper;

import es.javierserrano.domain.model.Author;
import es.javierserrano.domain.model.shared.Name;
import es.javierserrano.domain.model.shared.Slug;
import es.javierserrano.domain.repository.entity.AuthorEntity;
import es.javierserrano.domain.service.dto.AuthorDto;
import org.apache.commons.csv.CSVRecord;

public class AuthorMapper extends BaseMapper{

    public static AuthorDto toAuthorDto(CSVRecord csvRecord) {
        if (csvRecord == null) {
            return null;
        }

        return new AuthorDto(
                parseLong(csvRecord.get("id")),
                parseString(csvRecord.get("name")),
                parseString(csvRecord.get("nationality")),
                parseString(csvRecord.get("biography_es")),
                parseString(csvRecord.get("biography_es")),
                parseInt(csvRecord.get("birth_year")),
                parseInt(csvRecord.get("death_year")),
                parseString(csvRecord.get("slug"))
        );
    }

    public static Author toAuthor(CSVRecord csvRecord) {
        if (csvRecord == null) {
            return null;
        }

        return new Author(
                parseLong(csvRecord.get("id")),
                new Name(parseString(csvRecord.get("name"))),
                parseString(csvRecord.get("nationality")),
                parseString(csvRecord.get("biography_es")),
                parseString(csvRecord.get("biography_es")),
                parseInt(csvRecord.get("birth_year")),
                parseInt(csvRecord.get("death_year")),
                new Slug(parseString(csvRecord.get("slug")))
        );
    }

    public static AuthorEntity toAuthorEntity(CSVRecord csvRecord) {
        if (csvRecord == null) {
            return null;
        }

        return new AuthorEntity(
                parseLong(csvRecord.get("id")),
                parseString(csvRecord.get("name")),
                parseString(csvRecord.get("nationality")),
                parseString(csvRecord.get("biography_es")),
                parseString(csvRecord.get("biography_es")),
                parseInt(csvRecord.get("birth_year")),
                parseInt(csvRecord.get("death_year")),
                parseString(csvRecord.get("slug"))
        );
    }
}
