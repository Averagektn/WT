package by.bsuir.mycoolstore.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "film", schema = "mycoolstore")
public class FilmEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "flm_id")
    private Long flmId;

    public Long getFlmId() {
        return flmId;
    }

    public void setFlmId(Long flmId) {
        this.flmId = flmId;
    }

    @Basic
    @Column(name = "flm_description")
    private String flmDescription;

    public String getFlmDescription() {
        return flmDescription;
    }

    public void setFlmDescription(String flmDescription) {
        this.flmDescription = flmDescription;
    }

    @Basic
    @Column(name = "flm_price")
    private BigDecimal flmPrice;

    public BigDecimal getFlmPrice() {
        return flmPrice;
    }

    public void setFlmPrice(BigDecimal flmPrice) {
        this.flmPrice = flmPrice;
    }

    @Basic
    @Column(name = "flm_discount")
    private Object flmDiscount;

    public Object getFlmDiscount() {
        return flmDiscount;
    }

    public void setFlmDiscount(Object flmDiscount) {
        this.flmDiscount = flmDiscount;
    }

    @Basic
    @Column(name = "flm_author")
    private String flmAuthor;

    public String getFlmAuthor() {
        return flmAuthor;
    }

    public void setFlmAuthor(String flmAuthor) {
        this.flmAuthor = flmAuthor;
    }

    @Basic
    @Column(name = "flm_age")
    private Object flmAge;

    public Object getFlmAge() {
        return flmAge;
    }

    public void setFlmAge(Object flmAge) {
        this.flmAge = flmAge;
    }

    @Basic
    @Column(name = "flm_name")
    private String flmName;

    public String getFlmName() {
        return flmName;
    }

    public void setFlmName(String flmName) {
        this.flmName = flmName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmEntity that = (FilmEntity) o;

        if (!Objects.equals(flmId, that.flmId)) return false;
        if (!Objects.equals(flmDescription, that.flmDescription)) return false;
        if (!Objects.equals(flmPrice, that.flmPrice)) return false;
        if (!Objects.equals(flmDiscount, that.flmDiscount)) return false;
        if (!Objects.equals(flmAuthor, that.flmAuthor)) return false;
        if (!Objects.equals(flmAge, that.flmAge)) return false;
        return Objects.equals(flmName, that.flmName);
    }

    @Override
    public int hashCode() {
        int result = flmId != null ? flmId.hashCode() : 0;
        result = 31 * result + (flmDescription != null ? flmDescription.hashCode() : 0);
        result = 31 * result + (flmPrice != null ? flmPrice.hashCode() : 0);
        result = 31 * result + (flmDiscount != null ? flmDiscount.hashCode() : 0);
        result = 31 * result + (flmAuthor != null ? flmAuthor.hashCode() : 0);
        result = 31 * result + (flmAge != null ? flmAge.hashCode() : 0);
        result = 31 * result + (flmName != null ? flmName.hashCode() : 0);
        return result;
    }
}
