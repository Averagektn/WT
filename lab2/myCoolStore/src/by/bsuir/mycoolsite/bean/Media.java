package by.bsuir.mycoolsite.bean;

public class Media {
    private int id;
    private String trailerPath;
    private String filmPath;

    public Media(int id, String trailerPath, String filmPath) {
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
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
