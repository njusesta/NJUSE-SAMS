package org.nju.sesta.sams.entity;

import org.nju.sesta.sams.response.personalInfo.ContactInformation;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @NotNull
    @Column(length = 30)
    private String id;

    @NotNull
    private String passwordEncrypted;

    @NotNull
    @Column(unique = true)
    private String email;

    @Column(length = 20)
    private String name;

    @Embedded
    @Column(name = "contact_information")
    private ContactInformation contactInformation;

    @Column(length = 20)
    private String grade;

    @Column(length = 10)
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
    private Boolean enabled = true;
    @NotNull
    private  Boolean hasBeenExamined=false;
    @Column(name = "last_logout_date")
    @Temporal(TemporalType.DATE)
    private Calendar lastLogoutDate;

    @Column(name = "last_password_reset_date")
    @Temporal(TemporalType.DATE)
    private Calendar lastPasswordResetDate;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_activity",

            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "activity_ID", referencedColumnName = "id")})
    private List<Activity> activitiesJoined = new ArrayList<Activity>();

    @OneToMany(targetEntity = Activity.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, mappedBy = "creator")
    private List<Activity> activitiesReleased = new ArrayList<Activity>();

    @OneToMany(targetEntity = DevAxFormItem.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "owner")
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

    public String getPasswordEncrypted() {
        return passwordEncrypted;
    }

    public void setPasswordEncrypted(String passwordEncrypted) {
        this.passwordEncrypted = passwordEncrypted;
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

    public Boolean getHasBeenExamined() {
        return hasBeenExamined;
    }

    public void setHasBeenExamined(Boolean hasBeenExamined) {
        this.hasBeenExamined = hasBeenExamined;
    }
}
