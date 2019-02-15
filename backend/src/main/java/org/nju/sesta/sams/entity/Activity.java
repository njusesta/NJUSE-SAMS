package org.nju.sesta.sams.entity;

import org.nju.sesta.sams.enums.ActivityKind;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

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

    private String type;

    @Column(name = "way_to_register")
    private String way2Register;

    @Column(name = "reg_start_date")
    @Temporal(TemporalType.DATE)
    private Calendar regStartDate;

    @Column(name = "reg_end_date")
    @Temporal(TemporalType.DATE)
    private Calendar regEndDate;

    private Integer limited;

    private Calendar initDate;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private String creatorId;

    @ManyToMany(mappedBy = "activitiesJoined", fetch = FetchType.LAZY)
    private List<User> participants;

    @OneToMany(targetEntity = Group.class,
            cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "belongTo")
    private List<Group> groups;


    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
    return name;}

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getRegEndDate() {
        return regEndDate;
    }

    public void setRegEndDate(Calendar regEndDate) {
        this.regEndDate = regEndDate;
    }

    public ActivityKind getKind() {
        return kind;
    }

    public void setKind(ActivityKind kind) {
        this.kind = kind;
    }

    public Calendar getRegStartDate() {
        return regStartDate;
    }

    public void setRegStartDate(Calendar regStartDate) {
        this.regStartDate = regStartDate;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLimited() {
        return limited;
    }

    public void setLimited(Integer limited) {
        this.limited = limited;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWay2Register() {
        return way2Register;
    }

    public void setWay2Register(String way2Register) {
        this.way2Register = way2Register;
    }

    public Calendar getInitDate() {
        return initDate;
    }

    public void setInitDate(Calendar initDate) {
        this.initDate = initDate;
    }
}
