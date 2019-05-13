package org.nju.sesta.sams.entity;

import lombok.Data;
import org.nju.sesta.sams.enums.RoleName;

import javax.persistence.*;
import java.util.Calendar;

@Data
@Entity
@Table(name = "tbl_auth_up_request")
public class AuthUpRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentId;

    private RoleName targetRole;

    private Calendar releasedTime;


    public AuthUpRequest() {
        releasedTime = Calendar.getInstance();
    }
}
