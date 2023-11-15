package by.bsuir.mycoolsite.bean;

/**
 * Class representing a film category.
 */
public class Category {
    private long id;
    private String name;

    /**
     * Constructs a new Category with the specified ID and name.
     *
     * @param id   the ID of the category
     * @param name the name of the category
     */
    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Constructs a new Category with the specified ID and an empty name.
     *
     * @param id the ID of the category
     */
    public Category(long id) {
        this(id, "");
    }

    /**
     * Constructs a new Category with an undefined ID and the specified name.
     *
     * @param name the name of the category
     */
    public Category(String name) {
        this(-1, name);
    }

    /**
     * Gets the ID of the category.
     *
     * @return the ID of the category
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the ID of the category.
     *
     * @param id the new ID of the category
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the name of the category.
     *
     * @return the name of the category
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     *
     * @param name the new name of the category
     */
    public void setName(String name) {
        this.name = name;
    }
}

