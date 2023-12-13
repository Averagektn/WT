package by.bsuir.mycoolstore.entity;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * The CartEntity class represents a cart item in the database.
 */
@Entity
@Table(name = "cart", schema = "mycoolstore")
@IdClass(CartEntityPK.class)
public class CartEntity {
    @Id
    @Column(name = "crt_user")
    private Long crtUser;

    /**
     * Gets the ID of the user associated with the cart item.
     *
     * @return The ID of the user.
     */
    public Long getCrtUser() {
        return crtUser;
    }

    /**
     * Sets the ID of the user associated with the cart item.
     *
     * @param crtUser The ID of the user.
     */
    public void setCrtUser(Long crtUser) {
        this.crtUser = crtUser;
    }

    @Id
    @Column(name = "crt_film")
    private Long crtFilm;

    /**
     * Gets the ID of the film associated with the cart item.
     *
     * @return The ID of the film.
     */
    public Long getCrtFilm() {
        return crtFilm;
    }

    /**
     * Sets the ID of the film associated with the cart item.
     *
     * @param crtFilm The ID of the film.
     */
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
