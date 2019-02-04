package org.nju.sesta.sams.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @NotNull
    private String id;

    @NotNull
    @Size(min = 8, max = 50)
    private String passwordHash;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String grade;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<Role>();

    @NotNull
    private Boolean enabled;

    @Column(name = "last_logout_date")
    @Temporal(TemporalType.DATE)
    private Calendar lastLogoutDate;

    @Column(name = "last_password_reset_date")
    @Temporal(TemporalType.DATE)
    private Calendar lastPasswordResetDate;

    @NotNull
    @OneToMany(targetEntity = DevAxFormItem.class,
            cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "studentId")
    private Set<DevAxFormItem> DevAxForm = new HashSet<DevAxFormItem>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Set<DevAxFormItem> getDevAxForm() {
        return DevAxForm;
    }

    public void setDevAxForm(Set<DevAxFormItem> devAxForm) {
        DevAxForm = devAxForm;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Calendar getLastLogoutDate() {
        return lastLogoutDate;
    }

    public void setLastLogoutDate(Calendar lastLogoutDate) {
        this.lastLogoutDate = lastLogoutDate;
    }

    public Calendar getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Calendar lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
