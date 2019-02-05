package org.nju.sesta.sams.entity;

import org.nju.sesta.sams.response.PersonalInfo.ContactInformation;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

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

    @Column(name = "contact_information")
    private ContactInformation contactInformation;

    @NotNull
    private String grade;

    private String clazz;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles = new ArrayList<Role>();

    @NotNull
    private Boolean enabled;

    @Column(name = "last_logout_date")
    @Temporal(TemporalType.DATE)
    private Calendar lastLogoutDate;

    @Column(name = "last_password_reset_date")
    @Temporal(TemporalType.DATE)
    private Calendar lastPasswordResetDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_activity",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "activity_ID", referencedColumnName = "id")})
    private List<Activity> activitiesJoined = new ArrayList<Activity>();

    @NotNull
    @OneToMany(targetEntity = Activity.class,
            cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "studentId")
    private List<Activity> activitiesReleased = new ArrayList<Activity>();

    @NotNull
    @OneToMany(targetEntity = DevAxFormItem.class,
            cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "studentId")
    private List<DevAxFormItem> DevAxForm = new ArrayList<DevAxFormItem>();

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

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public List<DevAxFormItem> getDevAxForm() {
        return DevAxForm;
    }

    public void setDevAxForm(List<DevAxFormItem> devAxForm) {
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Activity> getActivitiesJoined() {
        return activitiesJoined;
    }

    public void setActivitiesJoined(List<Activity> activitiesJoined) {
        this.activitiesJoined = activitiesJoined;
    }

    public List<Activity> getActivitiesReleased() {
        return activitiesReleased;
    }

    public void setActivitiesReleased(List<Activity> activitiesReleased) {
        this.activitiesReleased = activitiesReleased;
    }
}
