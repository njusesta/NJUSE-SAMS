package org.nju.sesta.sams.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Data
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


}
