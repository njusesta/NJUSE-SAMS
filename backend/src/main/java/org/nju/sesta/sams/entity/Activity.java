package org.nju.sesta.sams.entity;

import org.nju.sesta.sams.enums.ActivityKind;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Set;

@Entity
@Table(name = "tbl_activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String content;

    @Enumerated(EnumType.STRING)
    private ActivityKind kind;

    @Column(name = "way_to_register")
    private String way2Register;

    @Column(name = "reg_start_date")
    @Temporal(TemporalType.DATE)
    private Calendar regStartDate;

    @Column(name = "reg_end_date")
    @Temporal(TemporalType.DATE)
    private Calendar regEndDate;

    private Boolean isLimited;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private String creator;

    @ManyToMany(mappedBy = "activitiesJoined", fetch = FetchType.LAZY)
    private Set<User> participants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ActivityKind getKind() {
        return kind;
    }

    public void setKind(ActivityKind kind) {
        this.kind = kind;
    }

    public String getWay2Register() {
        return way2Register;
    }

    public void setWay2Register(String way2Register) {
        this.way2Register = way2Register;
    }

    public Calendar getRegStartDate() {
        return regStartDate;
    }

    public void setRegStartDate(Calendar regStartDate) {
        this.regStartDate = regStartDate;
    }

    public Calendar getRegEndDate() {
        return regEndDate;
    }

    public void setRegEndDate(Calendar regEndDate) {
        this.regEndDate = regEndDate;
    }

    public Boolean getLimited() {
        return isLimited;
    }

    public void setLimited(Boolean limited) {
        isLimited = limited;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }
}
