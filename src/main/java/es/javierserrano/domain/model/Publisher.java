package es.javierserrano.domain.model;

public class Publisher {
    private Long id;
    private String name;
    private String slug;

    public Publisher(Long id, String name, String slug) {
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

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug.toString();
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}

