package org.nju.sesta.sams.parameter.activity;

import java.util.Calendar;

public class NewRecruitmentParameter {
    private String name;
    private String type;
    private String content;
    private Calendar regStartDate;
    private Calendar regEndDate;
    private Integer limitedNumber;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getLimitedNumber() {
        return limitedNumber;
    }

    public void setLimitedNumber(Integer limitedNumber) {
        this.limitedNumber = limitedNumber;
    }
}
