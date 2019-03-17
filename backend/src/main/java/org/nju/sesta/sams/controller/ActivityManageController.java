package org.nju.sesta.sams.controller;

import org.nju.sesta.sams.entity.Activity;
import org.nju.sesta.sams.entity.Applicant;
import org.nju.sesta.sams.exception.AuthorityException;
import org.nju.sesta.sams.exception.BasicException;
import org.nju.sesta.sams.factory.ActivityFactory;
import org.nju.sesta.sams.parameter.activity.NewActivityParameter;
import org.nju.sesta.sams.parameter.activity.NewMatchParameter;
import org.nju.sesta.sams.parameter.activity.NewRecruitmentParameter;
import org.nju.sesta.sams.response.activityInfo.ActivityInfoResponse;
import org.nju.sesta.sams.service.ActivityManageService;
import org.nju.sesta.sams.util.token.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/activity")
public class ActivityManageController {
    @Autowired
    ActivityManageService service;

    @Autowired
    JwtToken jwtToken;

    @Value("${jwt.header}")
    String tokenHeader;


    @RequestMapping(value = "/match/new",
            method = RequestMethod.POST)
    public ResponseEntity<?> applyForNewMatch(@RequestBody NewMatchParameter param, HttpServletRequest request) {

        Activity activity = ActivityFactory.create(param);
        String studentId = jwtToken.getUsernameFromRequest(request);
        service.applyForNewMatch(activity, studentId);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/activity",
            method = RequestMethod.PUT)
    public ResponseEntity<?> updateActivity(@RequestBody Activity activity, HttpServletRequest request) {
        String studentId = jwtToken.getUsernameFromRequest(request);
        if (studentId.equals(activity.getCreator().getId())) {
            service.updateActivity(activity);
            return ResponseEntity.ok(null);
        }
        throw new AuthorityException();
    }

    @RequestMapping(value = "/activity/new",
            method = RequestMethod.POST)
    public ResponseEntity<?> applyForNewActivity(@RequestBody NewActivityParameter param, HttpServletRequest request) {

        Activity activity = ActivityFactory.create(param);
        String studentId = jwtToken.getUsernameFromRequest(request);
        service.applyForNewActivity(activity, studentId);
        return ResponseEntity.ok(null);
    }


    @RequestMapping(value = "/recruitment/new",
            method = RequestMethod.POST)
    public ResponseEntity<?> applyForNewRecruitment(@RequestBody NewRecruitmentParameter param, HttpServletRequest request) {
        Activity activity = ActivityFactory.create(param);
        String studentId = jwtToken.getUsernameFromRequest(request);
        service.applyForNewRecruitment(activity, studentId);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/list",
            method = RequestMethod.GET)
    public ResponseEntity<?> getActivityList() {
        Activity[] activities = service.getActivityList();
        return ResponseEntity.ok(activities);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<?> getActivityDetail(@PathVariable String id) {
        return ResponseEntity.ok(new ActivityInfoResponse(service.getActivityDetail(Long.parseLong(id))));
    }

    @RequestMapping(value = "/signUp/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<?> signUpForActivity(@PathVariable Long id, HttpServletRequest request) {
        String studentId = jwtToken.getUsernameFromRequest(request);
        service.signUpForActivity(id, studentId);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/recruitment/form/{activityId}",
            method = RequestMethod.GET)
    public ResponseEntity<?> getApplicationForm(@PathVariable Long activityId, HttpServletRequest request) {
        String studentId = jwtToken.getUsernameFromRequest(request);
        service.matchActivityAndUser(activityId, studentId);
        return ResponseEntity.ok(service.getApplicationForm(activityId));
    }

    @RequestMapping(value = "/recruitment/form",
            method = RequestMethod.POST)
    public ResponseEntity<?> sendApplicationForm(@RequestBody Map<String, String> param, HttpServletRequest request) {
        String studentId = jwtToken.getUsernameFromRequest(request);
        service.sendApplicationForm(Long.parseLong(param.get("activityId")), param.get("description"), studentId);
        return ResponseEntity.ok(null);
    }

}
