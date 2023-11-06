package by.bsuir.mycoolsite.bean;

import by.bsuir.mycoolsite.bean.enums.AgeRestriction;

import java.math.BigDecimal;
import java.util.List;

public class Film {
    private long id;
    private String description;
    private BigDecimal price;
    private Media media;
    private List<Category> categories;
    private int discount;
    private String author;
    private AgeRestriction ageRestriction;
    private String name;

    public Film(long id, String description, BigDecimal price, Media media, int discount,
                String author, AgeRestriction ageRestriction, String name, List<Category> categories) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.media = media;
        this.categories = categories;
        this.discount = discount;
        this.author = author;
        this.ageRestriction = ageRestriction;
        this.name = name;
    }

    public Film(String description, BigDecimal price, Media media, int discount,
                String author, AgeRestriction ageRestriction, String name, List<Category> categories) {
        this(-1, description, price, media, discount, author, ageRestriction, name, categories);
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

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
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

    public void setId(long id) {
        this.id = id;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
