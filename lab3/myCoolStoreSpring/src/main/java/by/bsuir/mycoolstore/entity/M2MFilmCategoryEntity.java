package by.bsuir.mycoolstore.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "m2m_film_category", schema = "mycoolstore")
@IdClass(M2MFilmCategoryEntityPK.class)
public class M2MFilmCategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "fc_film")
    private Long fcFilm;

    public Long getFcFilm() {
        return fcFilm;
    }

    public void setFcFilm(Long fcFilm) {
        this.fcFilm = fcFilm;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "fc_category")
    private Long fcCategory;

    public Long getFcCategory() {
        return fcCategory;
    }

    public void setFcCategory(Long fcCategory) {
        this.fcCategory = fcCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        M2MFilmCategoryEntity that = (M2MFilmCategoryEntity) o;

        if (!Objects.equals(fcFilm, that.fcFilm)) return false;
        return Objects.equals(fcCategory, that.fcCategory);
    }

    @Override
    public int hashCode() {
        int result = fcFilm != null ? fcFilm.hashCode() : 0;
        result = 31 * result + (fcCategory != null ? fcCategory.hashCode() : 0);
        return result;
    }
}
