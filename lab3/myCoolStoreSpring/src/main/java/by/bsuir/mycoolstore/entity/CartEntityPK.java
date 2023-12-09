package by.bsuir.mycoolstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class CartEntityPK implements Serializable {
    @Column(name = "crt_user")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Object crtUser;
    @Column(name = "crt_film")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Object crtFilm;

    public Object getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(Object crtUser) {
        this.crtUser = crtUser;
    }

    public Object getCrtFilm() {
        return crtFilm;
    }

    public void setCrtFilm(Object crtFilm) {
        this.crtFilm = crtFilm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartEntityPK that = (CartEntityPK) o;

        if (!Objects.equals(crtUser, that.crtUser)) return false;
        return Objects.equals(crtFilm, that.crtFilm);
    }

    @Override
    public int hashCode() {
        int result = crtUser != null ? crtUser.hashCode() : 0;
        result = 31 * result + (crtFilm != null ? crtFilm.hashCode() : 0);
        return result;
    }
}
