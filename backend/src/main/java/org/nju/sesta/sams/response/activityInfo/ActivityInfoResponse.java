package org.nju.sesta.sams.response.activityInfo;

import org.nju.sesta.sams.entity.Activity;
import org.nju.sesta.sams.entity.Group;
import org.nju.sesta.sams.entity.User;
import org.nju.sesta.sams.enums.ActivityKind;

import java.util.Calendar;
import java.util.List;

public class ActivityInfoResponse {
    private long id;
    private String name;
    private String content;
    private ActivityKind kind;
    private String type;
    private String way2Register;
    private Calendar regStartDate;
    private Calendar regEndDate;
    private String creatorId;
    private Integer isLimited;
    private List<User> participants;
    private List<Group> groups;
    public ActivityInfoResponse(Activity activity) {
        id=activity.getId();
        name=activity.getName();
        content=activity.getContent();
        kind=activity.getKind();
        type=activity.getType();
        way2Register=activity.getWay2Register();
        regStartDate=activity.getRegStartDate();
        regEndDate=activity.getRegEndDate();
        creatorId =activity.getCreator().getId();
        isLimited=activity.getLimited();
        participants=activity.getParticipants();
        groups=activity.getGroups();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getIsLimited() {
        return isLimited;
    }

    public void setIsLimited(Integer isLimited) {
        this.isLimited = isLimited;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
