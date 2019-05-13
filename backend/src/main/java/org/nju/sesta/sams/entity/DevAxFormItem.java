package org.nju.sesta.sams.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Data
@Entity
@Table(name = "tbl_dev_ax_form_item")
public class DevAxFormItem {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String content;

    @NotNull
    private String rule;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Calendar date;

    @NotNull
    private Integer point;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private User owner;


}
