package es.javierserrano.domain.model.book;

import es.javierserrano.domain.exception.BusinessException;
import es.javierserrano.domain.model.Author;
import es.javierserrano.domain.model.Publisher;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private Long id;
    private Isbn isbn;
    private String titleEs;
    private String titleEn;
    private String synopsisEs;
    private String synopsisEn;
    private BasePrice basePrice;
    private double discountPercentage;
    private BigDecimal price;
    private String cover;
    private LocalDate publicationDate;
    private Publisher publisher;
    private List<Author> authors;

    public Book(
            Long id,
            Isbn isbn,
            String titleEs,
            String titleEn,
            String synopsisEs,
            String synopsisEn,
            BasePrice basePrice,
            double discountPercentage,
            String cover,
            LocalDate publicationDate,
            Publisher publisher,
            List<Author> authors
    ) {
        this.id = id;
        this.isbn = isbn;
        this.titleEs = titleEs;
        this.titleEn = titleEn;
        this.synopsisEs = synopsisEs;
        this.synopsisEn = synopsisEn;
        this.basePrice = basePrice;
        this.discountPercentage = discountPercentage;
        this.price = calculateFinalPrice();
        this.cover = cover;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        setAuthors(authors);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn.toString();
    }

    public void setIsbn(Isbn isbn) {
        this.isbn = isbn;
    }

    public String getTitleEs() {
        return titleEs;
    }

    public void setTitleEs(String titleEs) {
        this.titleEs= titleEs;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getSynopsisEs() {
        return synopsisEs;
    }

    public void setSynopsisEs(String synopsisEs) {
        this.synopsisEs = synopsisEs;
    }

    public String getSynopsisEn() {
        return synopsisEn;
    }

    public void setSynopsisEn(String synopsisEn) {
        this.synopsisEn = synopsisEn;
    }

    public BigDecimal getBasePrice() {
        return basePrice.getBasePrice();
    }

    public void setBasePrice(BasePrice basePrice) {
        this.basePrice = basePrice;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public BigDecimal calculateFinalPrice() {
        BigDecimal discount = basePrice.getBasePrice()
                .multiply(BigDecimal.valueOf(discountPercentage))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        if (discount.compareTo(BigDecimal.ZERO) <= 0) {
            return basePrice.getBasePrice().setScale(2, RoundingMode.HALF_UP);
        }

        return basePrice.getBasePrice().subtract(discount).setScale(2, RoundingMode.HALF_UP);
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        if(authors==null||authors.isEmpty()){
            this.authors = new ArrayList<>();
            return;
        }

        this.authors = new ArrayList<>(authors);
    }

    public void addAuthor(Author author) {
        if(authors.contains(author)){
            throw new BusinessException("Author already exists");
        }

        this.authors.add(author);
    }

}
