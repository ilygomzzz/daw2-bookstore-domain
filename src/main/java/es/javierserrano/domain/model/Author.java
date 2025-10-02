package es.javierserrano.domain.model;

import es.javierserrano.domain.model.shared.Name;
import es.javierserrano.domain.model.shared.Slug;

public class Author {
    private Long id;
    private Name name;
    private String nationality;
    private String biographyEs;
    private String biographyEn;
    private int birthYear;
    private Integer deathYear;
    private Slug slug;

    public Author(Long id, Name name, String nationality, String biographyEs, String biographyEn, int birthYear, Integer deathYear, Slug slug) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.biographyEs = biographyEs;
        this.biographyEn = biographyEn;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.slug = slug;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name.toString();
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBiographyEs() {
        return biographyEs;
    }

    public void setBiographyEs(String biographyEs) {
        this.biographyEs = biographyEs;
    }

    public String getBiographyEn() {
        return biographyEn;
    }

    public void setBiographyEn(String biographyEn) {
        this.biographyEn = biographyEn;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public String getSlug() {
        return slug.toString();
    }

    public void setSlug(Slug slug) {
        this.slug = slug;
    }
}
