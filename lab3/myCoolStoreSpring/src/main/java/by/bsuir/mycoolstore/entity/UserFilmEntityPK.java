package by.bsuir.mycoolstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class UserFilmEntityPK implements Serializable {
    @Column(name = "uf_user")
    @Id
    private Long ufUser;
    @Column(name = "uf_film")
    @Id
    private Long ufFilm;

    public Long getUfUser() {
        return ufUser;
    }

    public void setUfUser(Long ufUser) {
        this.ufUser = ufUser;
    }

    public Long getUfFilm() {
        return ufFilm;
    }

    public void setUfFilm(Long ufFilm) {
        this.ufFilm = ufFilm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserFilmEntityPK that = (UserFilmEntityPK) o;

        if (!Objects.equals(ufUser, that.ufUser)) return false;
        return Objects.equals(ufFilm, that.ufFilm);
    }

    @Override
    public int hashCode() {
        int result = ufUser != null ? ufUser.hashCode() : 0;
        result = 31 * result + (ufFilm != null ? ufFilm.hashCode() : 0);
        return result;
    }
}
