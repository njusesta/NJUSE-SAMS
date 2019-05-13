package org.nju.sesta.sams.entity;

import lombok.Data;
import org.nju.sesta.sams.response.personalInfo.ContactInformation;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_user")
@Lazy(value = false)
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
    private Boolean hasBeenExamined = false;
    @Column(name = "last_logout_date")
    @Temporal(TemporalType.DATE)
    private Calendar lastLogoutDate;

    @Column(name = "last_password_reset_date")
    @Temporal(TemporalType.DATE)
    private Calendar lastPasswordResetDate;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_activity",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "activity_id", referencedColumnName = "id")})
    private List<Activity> activitiesJoined = new ArrayList<Activity>();

    @OneToMany(targetEntity = Activity.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, mappedBy = "creator")
    private List<Activity> activitiesReleased = new ArrayList<Activity>();

    @OneToMany(targetEntity = DevAxFormItem.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "owner")
    private List<DevAxFormItem> DevAxForm = new ArrayList<DevAxFormItem>();


}
