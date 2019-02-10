package org.nju.sesta.sams.entity;

import org.nju.sesta.sams.enums.ActivityKind;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;
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



}
