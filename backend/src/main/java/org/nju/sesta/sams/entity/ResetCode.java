package org.nju.sesta.sams.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Data
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

}
