package org.nju.sesta.sams.controller;

import org.nju.sesta.sams.entity.Activity;
import org.nju.sesta.sams.entity.AuthUpRequest;
import org.nju.sesta.sams.entity.DevAxFormItem;
import org.nju.sesta.sams.enums.RoleName;
import org.nju.sesta.sams.exception.BasicException;
import org.nju.sesta.sams.parameter.PersonalInfo.AuthUpProcessParameter;
import org.nju.sesta.sams.parameter.PersonalInfo.BasicInfoParameter;
import org.nju.sesta.sams.parameter.PersonalInfo.DevFormInfoParameter;
import org.nju.sesta.sams.response.personalInfo.PersonalInfoResponse;
import org.nju.sesta.sams.service.ActivityManageService;
import org.nju.sesta.sams.service.PersonalInfoService;
import org.nju.sesta.sams.util.token.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class PersonalInfoController {

    @Autowired
    PersonalInfoService personalInfoService;

    @Autowired
    ActivityManageService activityManageService;

    @Autowired
    JwtUtil jwtUtil;

    @Value("${jwt.header}")
    String tokenHeader;

    @RequestMapping(value = "/info",
            method = RequestMethod.GET)
    public ResponseEntity<?> getPersonalInfo(@RequestParam(value = "id") String id, HttpServletRequest request) {
        if (!id.equals(jwtUtil.getUsernameFromRequest(request)))
            throw new BasicException(HttpStatus.BAD_REQUEST, "bad request");
        return ResponseEntity.ok(new PersonalInfoResponse(personalInfoService.getPersonalInfo(id)));
    }

    @RequestMapping(value = "/info",
            method = RequestMethod.PUT)
    public ResponseEntity<?> updatePersonalInfo(@RequestBody BasicInfoParameter parameter, HttpServletRequest request) {
        String id = jwtUtil.getUsernameFromRequest(request);
        personalInfoService.updateBasicInfo(id, parameter);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/authority",
            method = RequestMethod.POST)
    public ResponseEntity<?> applyForAuthUpdating(Map<String, String> param, HttpServletRequest request) {
        String id = jwtUtil.getUsernameFromRequest(request);
        personalInfoService.applyForAuthUpdating(RoleName.valueOf(param.get("targetAuthority")), id);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/authority/requests",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAuthUpRequests() {
        AuthUpRequest[] requests = personalInfoService.getAuthUpRequests();
        return ResponseEntity.ok(requests);
    }

    @RequestMapping(value = "/authority",
            method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> handleAuthUpRequest(@RequestBody AuthUpProcessParameter param) {
        personalInfoService.handleAuthUpRequest(param.getId(), param.getDecision());
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/info/activity/joined/list",
            method = RequestMethod.GET)
    public ResponseEntity<?> getActivitiesJoined(HttpServletRequest request) {
        String id = jwtUtil.getUsernameFromRequest(request);
        Activity[] activities = personalInfoService.getActivityJoined(id);
        return ResponseEntity.ok(activities);
    }

    @RequestMapping(value = "/info/activity/released/list",
            method = RequestMethod.GET)
    public ResponseEntity<?> getActivitiesReleased(HttpServletRequest request) {
        String id = jwtUtil.getUsernameFromRequest(request);
        Activity[] activities = personalInfoService.getActivityReleased(id);
        return ResponseEntity.ok(activities);
    }

    @RequestMapping(value = "/info/recruitment/released/list",
            method = RequestMethod.GET)
    public ResponseEntity<?> getRecruitmentReleased(HttpServletRequest request) {
        String id = jwtUtil.getUsernameFromRequest(request);
        Activity[] activities = personalInfoService.getActivityReleased(id);
        return ResponseEntity.ok(activities);
    }

//    @RequestMapping(value = "/info/activity/joed{activityId}",
//            method = RequestMethod.GET)
//    public ResponseEntity<?> getActivityDetail(@PathVariable String activityId) {
//        return ResponseEntity.ok(new ActivityInfoResponse(activityManageService.getActivityDetail(Long.parseLong(activityId))));
//    }

    @RequestMapping(value = "/info/dev_ax_form",
            method = RequestMethod.GET)
    public ResponseEntity<?> getDevAxForm(HttpServletRequest request) {
        String id = jwtUtil.getUsernameFromRequest(request);
        DevAxFormItem[] items = personalInfoService.getDev(id);
        return ResponseEntity.ok(items);
    }

    @RequestMapping(value = "/info/dev_ax_form",
            method = RequestMethod.PUT)
    public ResponseEntity<?> updateDevAxForm(@RequestBody DevFormInfoParameter devAxFormItems, HttpServletRequest request) {
        String id = jwtUtil.getUsernameFromRequest(request);
        personalInfoService.updateDevAxFormInfo(id, devAxFormItems);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/info/dev_ax_form/responsible",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('MONITOR')")
    public ResponseEntity<?> getResponsibleDevAxForm(HttpServletRequest request) {
        //TODO 获取班长负责的所有学生的发展测评表
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/info/dev_ax_form/responsible",
            method = RequestMethod.PUT)
    @PreAuthorize("hasRole('MONITOR')")
    public ResponseEntity<?> examineDevAxForm(HttpServletRequest request) {
        //TODO 班长审核发展测评表
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/class/student/list",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('MONITOR')")
    public ResponseEntity<?> getClassStudentList(HttpServletRequest request) {
        //TODO 班长获取已在系统中注册的本班学生名单
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


}
