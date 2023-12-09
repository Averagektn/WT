package by.bsuir.mycoolstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "user_film", schema = "mycoolstore")
@jakarta.persistence.IdClass(by.bsuir.mycoolstore.entity.UserFilmEntityPK.class)
public class UserFilmEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "uf_user")
    private Long ufUser;

    public Long getUfUser() {
        return ufUser;
    }

    public void setUfUser(Long ufUser) {
        this.ufUser = ufUser;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "uf_film")
    private Long ufFilm;

    public Object getUfFilm() {
        return ufFilm;
    }

    public void setUfFilm(Long ufFilm) {
        this.ufFilm = ufFilm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserFilmEntity that = (UserFilmEntity) o;

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
