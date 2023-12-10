package by.bsuir.mycoolstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class M2MFilmCategoryEntityPK implements Serializable {
    @Column(name = "fc_film")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fcFilm;
    @Column(name = "fc_category")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fcCategory;

    public Long getFcFilm() {
        return fcFilm;
    }

    public void setFcFilm(Long fcFilm) {
        this.fcFilm = fcFilm;
    }

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

        M2MFilmCategoryEntityPK that = (M2MFilmCategoryEntityPK) o;

        if (!Objects.equals(fcFilm, that.fcFilm)) return false;
        if (!Objects.equals(fcCategory, that.fcCategory)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fcFilm != null ? fcFilm.hashCode() : 0;
        result = 31 * result + (fcCategory != null ? fcCategory.hashCode() : 0);
        return result;
    }
}
