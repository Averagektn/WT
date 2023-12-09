package by.bsuir.mycoolstore.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cart", schema = "mycoolstore")
@IdClass(CartEntityPK.class)
public class CartEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "crt_user")
    private Long crtUser;

    public Long getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(Long crtUser) {
        this.crtUser = crtUser;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "crt_film")
    private Long crtFilm;

    public Long getCrtFilm() {
        return crtFilm;
    }

    public void setCrtFilm(Long crtFilm) {
        this.crtFilm = crtFilm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartEntity that = (CartEntity) o;

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
