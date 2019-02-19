package org.nju.sesta.sams.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Entity
@Table(name = "tbl_dev_ax_form_item")
public class DevAxFormItem {
    @Id
    @NotNull
    @Column(name = "Id", unique = true)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Calendar getDate() {
        return (Calendar)date.clone();
    }

    public void setDate(Calendar date) {
        this.date = (Calendar)date.clone();
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}
