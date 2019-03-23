package org.nju.sesta.sams.entity;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "tbl_mail_captcha")
public class MailCaptcha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String emailAddress;

    String code;

    @Temporal(TemporalType.TIMESTAMP)
    Calendar builtTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Calendar getBuiltTime() {
        return builtTime;
    }

    public void setBuiltTime(Calendar builtTime) {
        this.builtTime = builtTime;
    }
}
