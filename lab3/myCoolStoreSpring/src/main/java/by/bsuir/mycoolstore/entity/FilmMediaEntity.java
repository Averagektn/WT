package by.bsuir.mycoolstore.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "film_media", schema = "mycoolstore")
public class FilmMediaEntity {
    @Id
    @Column(name = "fm_id")
    private Long fmId;

    public Long getFmId() {
        return fmId;
    }

    public void setFmId(Long fmId) {
        this.fmId = fmId;
    }

    @Basic
    @Column(name = "fm_film_path")
    private String fmFilmPath;

    public String getFmFilmPath() {
        return fmFilmPath;
    }

    public void setFmFilmPath(String fmFilmPath) {
        this.fmFilmPath = fmFilmPath;
    }

    @Basic
    @Column(name = "fm_trailer_path")
    private String fmTrailerPath;

    public String getFmTrailerPath() {
        return fmTrailerPath;
    }

    public void setFmTrailerPath(String fmTrailerPath) {
        this.fmTrailerPath = fmTrailerPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmMediaEntity that = (FilmMediaEntity) o;

        if (!Objects.equals(fmId, that.fmId)) return false;
        if (!Objects.equals(fmFilmPath, that.fmFilmPath)) return false;
        return Objects.equals(fmTrailerPath, that.fmTrailerPath);
    }

    @Override
    public int hashCode() {
        int result = fmId != null ? fmId.hashCode() : 0;
        result = 31 * result + (fmFilmPath != null ? fmFilmPath.hashCode() : 0);
        result = 31 * result + (fmTrailerPath != null ? fmTrailerPath.hashCode() : 0);
        return result;
    }
}
