package org.nju.sesta.sams.entity;

import org.nju.sesta.sams.enums.RoleName;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "tbl_auth_up_request")
public class AuthUpRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentId;

    private RoleName targetRole;

    private Calendar releasedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public RoleName getTargetRole() {
        return targetRole;
    }

    public void setTargetRole(RoleName targetRole) {
        this.targetRole = targetRole;
    }

    public Calendar getReleasedTime() {
        return releasedTime;
    }

    public void setReleasedTime(Calendar releasedTime) {
        this.releasedTime = releasedTime;
    }

    public AuthUpRequest() {
        releasedTime = Calendar.getInstance();
    }
}
