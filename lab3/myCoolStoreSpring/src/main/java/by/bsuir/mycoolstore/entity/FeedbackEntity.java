package by.bsuir.mycoolstore.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "feedback", schema = "mycoolstore")
public class FeedbackEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "fbk_id")
    private Long fbkId;

    public Long getFbkId() {
        return fbkId;
    }

    public void setFbkId(Long fbkId) {
        this.fbkId = fbkId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fbk_author", referencedColumnName = "usr_id")
    private UserEntity fbkAuthor;

    public UserEntity getFbkAuthor() {
        return fbkAuthor;
    }

    public void setFbkAuthor(UserEntity fbkAuthor) {
        this.fbkAuthor = fbkAuthor;
    }

    @Basic
    @Column(name = "fbk_film")
    private Long fbkFilm;

    public Long getFbkFilm() {
        return fbkFilm;
    }

    public void setFbkFilm(Long fbkFilm) {
        this.fbkFilm = fbkFilm;
    }

    @Basic
    @Column(name = "fbk_text")
    private String fbkText;

    public String getFbkText() {
        return fbkText;
    }

    public void setFbkText(String fbkText) {
        this.fbkText = fbkText;
    }

    @Basic
    @Column(name = "fbk_rating")
    private Short fbkRating;

    public Short getFbkRating() {
        return fbkRating;
    }

    public void setFbkRating(Short fbkRating) {
        this.fbkRating = fbkRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeedbackEntity that = (FeedbackEntity) o;

        if (!Objects.equals(fbkId, that.fbkId)) return false;
        if (!Objects.equals(fbkAuthor, that.fbkAuthor)) return false;
        if (!Objects.equals(fbkFilm, that.fbkFilm)) return false;
        if (!Objects.equals(fbkText, that.fbkText)) return false;
        return Objects.equals(fbkRating, that.fbkRating);
    }

    @Override
    public int hashCode() {
        int result = fbkId != null ? fbkId.hashCode() : 0;
        result = 31 * result + (fbkAuthor != null ? fbkAuthor.hashCode() : 0);
        result = 31 * result + (fbkFilm != null ? fbkFilm.hashCode() : 0);
        result = 31 * result + (fbkText != null ? fbkText.hashCode() : 0);
        result = 31 * result + (fbkRating != null ? fbkRating.hashCode() : 0);
        return result;
    }
}
