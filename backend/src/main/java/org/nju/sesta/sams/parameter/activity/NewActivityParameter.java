package org.nju.sesta.sams.parameter.activity;

import java.util.Calendar;

public class NewActivityParameter {
    private String name;
    private String content;
    private String type;
    private String way2Register;
    private Calendar regStartDate;
    private Calendar regEndDate;
    private Integer limited;

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

    public Integer getLimited() {
        return limited;
    }

    public void setLimited(Integer limited) {
        this.limited = limited;
    }
}
