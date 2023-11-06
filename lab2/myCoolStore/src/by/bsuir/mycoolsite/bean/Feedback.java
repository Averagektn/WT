package by.bsuir.mycoolsite.bean;

public class Feedback {
    private long id;
    private long author;
    private long film;
    private String text;
    private int rating;

    public Feedback(long id, long author, long film, String text, int rating) {
        this.id = id;
        this.author = author;
        this.film = film;
        this.rating = rating;
        this.text = text;
    }

    public Feedback(long author, long film, String text, int rating) {
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

    public void setAuthor(long author) {
        this.author = author;
    }

    public long getAuthor() {
        return author;
    }

    public long getFilm() {
        return film;
    }

    public String getText() {
        return text;
    }

    public void setFilm(long film) {
        this.film = film;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setText(String text) {
        this.text = text;
    }
}
