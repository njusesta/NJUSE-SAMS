package org.nju.sesta.sams.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.nju.sesta.sams.enums.ActivityKind;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_activity")
public class Activity {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    private String content;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ActivityKind kind;

    @Column(length = 20)
    private String type;

    @Column(name = "way_to_register", length = 20)
    private String way2Register;

    @Column(name = "reg_start_date", length = 30)
    @Temporal(TemporalType.DATE)
    private Calendar regStartDate;

    @Column(name = "reg_end_date", length = 30)
    @Temporal(TemporalType.DATE)
    private Calendar regEndDate;

    private Integer limited;

    @Temporal(TemporalType.DATE)
    @Column(length = 30)
    private Calendar initDate;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private User creator;

    @ManyToMany(mappedBy = "activitiesJoined", fetch = FetchType.LAZY)
    private List<User> participants;

    @OneToMany(targetEntity = Group.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "belongTo")
    private List<Group> groups;

    @Column(length = 10)
    private Boolean passed;

}
