package org.nju.sesta.sams.parameter.activity;

import java.util.Calendar;

public class NewMatchParameter {
    private String name;
    private String content;
    private String way2Register;
    private Calendar regStartDate;
    private Calendar regEndDate;
    private Boolean isLimited;

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
}
