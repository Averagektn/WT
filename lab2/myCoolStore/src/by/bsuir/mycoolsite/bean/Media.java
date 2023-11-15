package by.bsuir.mycoolsite.bean;

/**
 * Class representing media content associated with a film.
 */
public class Media {
    private long id;
    private String trailerPath;
    private String filmPath;

    /**
     * Constructs a new Media with the specified ID.
     *
     * @param id the ID of the media
     */
    public Media(long id) {
        this(id, "", "");
    }

    /**
     * Constructs a new Media with the specified ID, trailer path, and film path.
     *
     * @param id          the ID of the media
     * @param trailerPath the path to the trailer of the film
     * @param filmPath    the path to the main film content
     */
    public Media(long id, String trailerPath, String filmPath) {
        this.id = id;
        this.trailerPath = trailerPath;
        this.filmPath = filmPath;
    }

    /**
     * Constructs a new Media with an undefined ID and the specified trailer path and film path.
     *
     * @param trailerPath the path to the trailer of the film
     * @param filmPath    the path to the main film content
     */
    public Media(String trailerPath, String filmPath) {
        this(-1, trailerPath, filmPath);
    }

    /**
     * Constructs a new Media with an undefined ID and the specified trailer path.
     *
     * @param trailerPath the path to the trailer of the film
     */
    public Media(String trailerPath) {
        this(-1, trailerPath, "");
    }

    /**
     * Gets the ID of the media.
     *
     * @return the ID of the media
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the ID of the media.
     *
     * @param id the new ID of the media
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the path to the main film content.
     *
     * @return the path to the main film content
     */
    public String getFilmPath() {
        return filmPath;
    }

    /**
     * Gets the path to the trailer of the film.
     *
     * @return the path to the trailer of the film
     */
    public String getTrailerPath() {
        return trailerPath;
    }

    /**
     * Sets the path to the main film content.
     *
     * @param filmPath the new path to the main film content
     */
    public void setFilmPath(String filmPath) {
        this.filmPath = filmPath;
    }

    /**
     * Sets the path to the trailer of the film.
     *
     * @param trailerPath the new path to the trailer of the film
     */
    public void setTrailerPath(String trailerPath) {
        this.trailerPath = trailerPath;
    }
}
