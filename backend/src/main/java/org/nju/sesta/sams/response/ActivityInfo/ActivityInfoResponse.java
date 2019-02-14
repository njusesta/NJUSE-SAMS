package org.nju.sesta.sams.response.ActivityInfo;

import org.nju.sesta.sams.entity.Activity;
import org.nju.sesta.sams.entity.User;
import org.nju.sesta.sams.enums.ActivityKind;

import java.util.Calendar;
import java.util.Set;

public class ActivityInfoResponse {
    private long id;
    private String name;
    private String content;
    private ActivityKind kind;
    private String way2Register;
    private Calendar regStartDate;
    private Calendar regEndDate;
    private String creater;
    private Boolean isLimited;
    private Set<User> participants;
    public ActivityInfoResponse(Activity activity) {
        id=activity.getId();
        name=activity.getName();
        content=activity.getContent();
        kind=activity.getKind();
        way2Register=activity.getWay2Register();
        regStartDate=activity.getRegStartDate();
        regEndDate=activity.getRegEndDate();
        creater=activity.getCreator();
        isLimited=activity.getLimited();
        participants=activity.getParticipants();
    }
}
