package by.bsuir.mycoolsite.bean;

import by.bsuir.mycoolsite.bean.enums.AgeRestriction;

import java.math.BigDecimal;

public class Film {
    private long id;
    private String description;
    private BigDecimal price;
    private long filmFileId;
    private long trailerId;
    private int discount;
    private String author;
    private AgeRestriction ageRestriction;
    private String name;

    public Film(long id, String description, BigDecimal price, long filmFileId, long trailerId, int discount,
                String author, AgeRestriction ageRestriction, String name) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.filmFileId = filmFileId;
        this.trailerId = trailerId;
        this.discount = discount;
        this.author = author;
        this.ageRestriction = ageRestriction;
        this.name = name;
    }

    public Film(String description, BigDecimal price, long filmFileId, long trailerId, int discount,
                String author, AgeRestriction ageRestriction, String name) {
        this(-1, description, price, filmFileId, trailerId, discount, author, ageRestriction, name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public long getFilmFileId() {
        return filmFileId;
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

    public long getTrailerId() {
        return trailerId;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public void setFilmFileId(long filmFileId) {
        this.filmFileId = filmFileId;
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

    public void setTrailerId(long trailerId) {
        this.trailerId = trailerId;
    }

    public void setId(long id) {
        this.id = id;
    }

}
