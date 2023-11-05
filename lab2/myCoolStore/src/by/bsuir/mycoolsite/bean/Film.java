package by.bsuir.mycoolsite.bean;

import java.math.BigDecimal;

public class Film {
    private long id;
    private String description;
    private BigDecimal price;
    private String filmFilepath;
    private String trailerFilepath;
    private int discount;
    private String author;
    private AgeRestriction ageRestriction;

    public Film(long id, String description, BigDecimal price, String filmFilepath, String trailerFilepath, int discount,
                String author, AgeRestriction ageRestriction) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.filmFilepath = filmFilepath;
        this.trailerFilepath = trailerFilepath;
        this.discount = discount;
        this.author = author;
        this.ageRestriction = ageRestriction;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getFilmFilepath() {
        return filmFilepath;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public String getTrailerFilepath() {
        return trailerFilepath;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public void setFilmFilepath(String filmFilepath) {
        this.filmFilepath = filmFilepath;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setTrailerFilepath(String trailerFilepath) {
        this.trailerFilepath = trailerFilepath;
    }

    public void setId(long id) {
        this.id = id;
    }

}
