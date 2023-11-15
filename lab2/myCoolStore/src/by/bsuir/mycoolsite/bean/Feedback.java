package by.bsuir.mycoolsite.bean;

/**
 * Class representing user feedback for a film.
 */
public class Feedback {
    private long id;
    private User author;
    private Film film;
    private String text;
    private int rating;

    /**
     * Constructs a new Feedback with the specified ID, author, film, text, and rating.
     *
     * @param id      the ID of the feedback
     * @param author  the User object representing the author of the feedback
     * @param film    the Film object representing the film associated with the feedback
     * @param text    the text content of the feedback
     * @param rating  the rating assigned to the film in the feedback
     */
    public Feedback(long id, User author, Film film, String text, int rating) {
        this.id = id;
        this.author = author;
        this.film = film;
        this.text = text;
        this.rating = rating;
    }

    /**
     * Constructs a new Feedback with an undefined ID, the specified author, film, text, and rating.
     *
     * @param author  the User object representing the author of the feedback
     * @param film    the Film object representing the film associated with the feedback
     * @param text    the text content of the feedback
     * @param rating  the rating assigned to the film in the feedback
     */
    public Feedback(User author, Film film, String text, int rating) {
        this(-1, author, film, text, rating);
    }

    /**
     * Gets the ID of the feedback.
     *
     * @return the ID of the feedback
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the ID of the feedback.
     *
     * @param id the new ID of the feedback
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the author of the feedback.
     *
     * @return the User object representing the author of the feedback
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Sets the author of the feedback.
     *
     * @param author the new User object representing the author of the feedback
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * Gets the film associated with the feedback.
     *
     * @return the Film object representing the film associated with the feedback
     */
    public Film getFilm() {
        return film;
    }

    /**
     * Sets the film associated with the feedback.
     *
     * @param film the new Film object representing the film associated with the feedback
     */
    public void setFilm(Film film) {
        this.film = film;
    }

    /**
     * Gets the text content of the feedback.
     *
     * @return the text content of the feedback
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text content of the feedback.
     *
     * @param text the new text content of the feedback
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the rating assigned to the film in the feedback.
     *
     * @return the rating assigned to the film in the feedback
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets the rating assigned to the film in the feedback.
     *
     * @param rating the new rating assigned to the film in the feedback
     */
    public void setRating(int rating) {
        this.rating = rating;
    }
}
