package by.bsuir.mycoolsite.bean;

public class Feedback {
    private long id;
    private User author;
    private Film film;
    private String text;
    private int rating;

    public Feedback(long id, User author, Film film, String text, int rating) {
        this.id = id;
        this.author = author;
        this.film = film;
        this.rating = rating;
        this.text = text;
    }

    public Feedback(User author, Film film, String text, int rating) {
        this(-1, author, film, text, rating);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getAuthor() {
        return author;
    }

    public Film getFilm() {
        return film;
    }

    public String getText() {
        return text;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setText(String text) {
        this.text = text;
    }
}
