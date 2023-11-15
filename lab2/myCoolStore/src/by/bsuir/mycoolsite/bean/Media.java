package by.bsuir.mycoolsite.bean;

public class Media {
    private long id;
    private String trailerPath;
    private String filmPath;

    public Media(long id) {
        this(id, "", "");
    }

    public Media(long id, String trailerPath, String filmPath) {
        this.id = id;
        this.trailerPath = trailerPath;
        this.filmPath = filmPath;
    }

    public Media(String trailerPath, String filmPath) {
        this(-1, trailerPath, filmPath);
    }

    public Media(String trailerPath) {
        this(-1, trailerPath, "");
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getFilmPath() {
        return filmPath;
    }

    public String getTrailerPath() {
        return trailerPath;
    }

    public void setFilmPath(String filmPath) {
        this.filmPath = filmPath;
    }

    public void setTrailerPath(String trailerPath) {
        this.trailerPath = trailerPath;
    }
}
