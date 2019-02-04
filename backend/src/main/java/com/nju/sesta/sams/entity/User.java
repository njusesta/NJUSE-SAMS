package com.nju.sesta.sams.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    private String password;

    @NotNull
    @Column(unique = true)
    private String emailAddress;

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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
