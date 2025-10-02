package es.javierserrano.domain.model;

import es.javierserrano.domain.model.shared.Name;
import es.javierserrano.domain.model.shared.Slug;

public class Publisher {
    private Long id;
    private Name name;
    private Slug slug;

    public Publisher(Long id, Name name, Slug slug) {
        this.id = id;
        this.name = name;
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

    public String getSlug() {
        return slug.toString();
    }

    public void setSlug(Slug slug) {
        this.slug = slug;
    }
}

