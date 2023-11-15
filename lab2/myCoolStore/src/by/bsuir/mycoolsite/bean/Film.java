package by.bsuir.mycoolsite.bean;

import by.bsuir.mycoolsite.bean.enums.AgeRestriction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a film in the system.
 */
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

    /**
     * Constructs a new Film with the specified ID.
     *
     * @param id the ID of the film
     */
    public Film(long id) {
        this.id = id;
        this.description = "";
        this.price = BigDecimal.ZERO;
        this.media = new Media(0);
        this.categories = new ArrayList<>();
        this.discount = 0;
        this.author = "";
        this.ageRestriction = AgeRestriction.EMPTY;
        this.name = "";
    }

    /**
     * Constructs a new Film with the specified ID, description, price, media, discount,
     * author, age restriction, name, and categories.
     *
     * @param id             the ID of the film
     * @param description    the description of the film
     * @param price          the price of the film
     * @param media          the Media object representing the media files of the film
     * @param discount       the discount percentage for the film
     * @param author         the author of the film
     * @param ageRestriction the AgeRestriction object representing the age restriction of the film
     * @param name           the name of the film
     * @param categories     the list of Category objects representing the categories of the film
     */
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

    /**
     * Constructs a new Film with an undefined ID and the specified description, price, media, discount,
     * author, age restriction, name, and categories.
     *
     * @param description    the description of the film
     * @param price          the price of the film
     * @param media          the Media object representing the media files of the film
     * @param discount       the discount percentage for the film
     * @param author         the author of the film
     * @param ageRestriction the AgeRestriction object representing the age restriction of the film
     * @param name           the name of the film
     * @param categories     the list of Category objects representing the categories of the film
     */
    public Film(String description, BigDecimal price, Media media, int discount,
                String author, AgeRestriction ageRestriction, String name, List<Category> categories) {
        this(-1, description, price, media, discount, author, ageRestriction, name, categories);
    }

    /**
     * Calculates the real price of the film after applying the discount.
     *
     * @return the real price of the film
     */
    public BigDecimal getRealPrice() {
        float percent = 1 - (float) discount / 100;
        BigDecimal result = price.multiply(BigDecimal.valueOf(percent));

        result = result.setScale(2, RoundingMode.HALF_UP);

        return result;
    }

    /**
     * Gets the name of the film.
     *
     * @return the name of the film
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the film.
     *
     * @param name the new name of the film
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the ID of the film.
     *
     * @return the ID of the film
     */
    public long getId() {
        return id;
    }

    /**
     * Gets the description of the film.
     *
     * @return the description of the film
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the author of the film.
     *
     * @return the author of the film
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets the age restriction of the film.
     *
     * @return the AgeRestriction object representing the age restriction of the film
     */
    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    /**
     * Gets the price of the film.
     *
     * @return the price of the film
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Gets the discount percentage for the film.
     *
     * @return the discount percentage for the film
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * Sets the age restriction of the film.
     *
     * @param ageRestriction the new AgeRestriction object representing the age restriction of the film
     */
    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    /**
     * Sets the author of the film.
     *
     * @param author the new author of the film
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Sets the description of the film.
     *
     * @param description the new description of the film
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the discount percentage for the film.
     *
     * @param discount the new discount percentage for the film
     */
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    /**
     * Sets the price of the film.
     *
     * @param price the new price of the film
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Sets the ID of the film.
     *
     * @param id the new ID of the film
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the Media object representing the media files of the film.
     *
     * @return the Media object representing the media files of the film
     */
    public Media getMedia() {
        return media;
    }

    /**
     * Sets the Media object representing the media files of the film.
     *
     * @param media the new Media object representing the media files of the film
     */
    public void setMedia(Media media) {
        this.media = media;
    }

    /**
     * Gets the list of Category objects representing the categories of the film.
     *
     * @return the list of Category objects representing the categories of the film
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * Sets the list of Category objects representing the categories of the film.
     *
     * @param categories the new list of Category objects representing the categories of the film
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
