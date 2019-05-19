package org.nju.sesta.sams.factory;

import org.nju.sesta.sams.entity.Activity;
import org.nju.sesta.sams.enums.ActivityKind;
import org.nju.sesta.sams.parameter.activity.NewActivityParameter;
import org.nju.sesta.sams.parameter.activity.NewMatchParameter;
import org.nju.sesta.sams.parameter.activity.NewRecruitmentParameter;

import java.util.Calendar;

public class ActivityFactory {
    public static Activity create(NewMatchParameter p) {
        Activity a = new Activity();
        a.setName(p.getName());
        a.setContent(p.getContent());
        a.setInitDate(Calendar.getInstance());
        a.setKind(ActivityKind.MATCH);
        a.setLimited(p.getLimitedNumber());
        a.setRegStartDate(p.getRegStartDate());
        a.setRegEndDate(p.getRegEndDate());
        a.setWay2Register(p.getWay2Register());
        a.setPassed(false);
        return a;
    }

    public static Activity create(NewActivityParameter p) {
        Activity a = new Activity();
        a.setName(p.getName());
        a.setContent(p.getContent());
        a.setType(p.getType());
        a.setInitDate(Calendar.getInstance());
        a.setKind(ActivityKind.OTHERS);
        a.setLimited(p.getLimitedNumber());
        a.setRegStartDate(p.getRegStartDate());
        a.setRegEndDate(p.getRegEndDate());
        a.setWay2Register(p.getWay2Register());
        a.setPassed(false);
        return a;
    }

    public static Activity create(NewRecruitmentParameter p) {
        Activity a = new Activity();
        a.setName(p.getName());
        a.setContent(p.getContent());
        a.setType(p.getType());
        a.setInitDate(Calendar.getInstance());
        a.setKind(ActivityKind.RECRUITMENT);
        a.setLimited(p.getLimitedNumber());
        a.setRegStartDate(p.getRegStartDate());
        a.setRegEndDate(p.getRegEndDate());
        a.setPassed(false);
        return a;
    }


}
