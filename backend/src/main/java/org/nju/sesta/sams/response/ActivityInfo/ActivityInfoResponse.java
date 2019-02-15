package org.nju.sesta.sams.response.ActivityInfo;

import org.nju.sesta.sams.entity.Activity;
import org.nju.sesta.sams.entity.Group;
import org.nju.sesta.sams.entity.User;
import org.nju.sesta.sams.enums.ActivityKind;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class ActivityInfoResponse {
    private long id;
    private String name;
    private String content;
    private ActivityKind kind;
    private String type;
    private String way2Register;
    private Calendar regStartDate;
    private Calendar regEndDate;
    private String createrid;
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
        createrid=activity.getCreatorId();
        isLimited=activity.getLimited();
        participants=activity.getParticipants();
        groups=activity.getGroups();
    }
}
