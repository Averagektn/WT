package by.bsuir.mycoolstore.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "user", schema = "mycoolstore")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "usr_id")
    private Long usrId;

    public Long getUsrId() {
        return usrId;
    }

    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    @Basic
    @Column(name = "usr_email")
    private String usrEmail;

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    @Basic
    @Column(name = "usr_password")
    private String usrPassword;

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    @Basic
    @Column(name = "usr_role")
    private Object usrRole;

    public Object getUsrRole() {
        return usrRole;
    }

    public void setUsrRole(Object usrRole) {
        this.usrRole = usrRole;
    }

    @Basic
    @Column(name = "usr_banned_by")
    private Object usrBannedBy;

    public Object getUsrBannedBy() {
        return usrBannedBy;
    }

    public void setUsrBannedBy(Object usrBannedBy) {
        this.usrBannedBy = usrBannedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (!Objects.equals(usrId, that.usrId)) return false;
        if (!Objects.equals(usrEmail, that.usrEmail)) return false;
        if (!Objects.equals(usrPassword, that.usrPassword)) return false;
        if (!Objects.equals(usrRole, that.usrRole)) return false;
        return Objects.equals(usrBannedBy, that.usrBannedBy);
    }

    @Override
    public int hashCode() {
        int result = usrId != null ? usrId.hashCode() : 0;
        result = 31 * result + (usrEmail != null ? usrEmail.hashCode() : 0);
        result = 31 * result + (usrPassword != null ? usrPassword.hashCode() : 0);
        result = 31 * result + (usrRole != null ? usrRole.hashCode() : 0);
        result = 31 * result + (usrBannedBy != null ? usrBannedBy.hashCode() : 0);
        return result;
    }
}
