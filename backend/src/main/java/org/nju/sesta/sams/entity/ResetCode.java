package org.nju.sesta.sams.entity;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "tbl_reset_code")
public class ResetCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Calendar createdDate;

    @Column(name = "expired_date")
    @Temporal(TemporalType.DATE)
    private Calendar expiredDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Calendar getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }

    public Calendar getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Calendar expiredDate) {
        this.expiredDate = expiredDate;
    }
}
