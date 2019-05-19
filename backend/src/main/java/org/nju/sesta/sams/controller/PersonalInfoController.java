package org.nju.sesta.sams.controller;

import org.nju.sesta.sams.entity.AuthUpRequest;
import org.nju.sesta.sams.entity.DevAxFormItem;
import org.nju.sesta.sams.enums.RoleName;
import org.nju.sesta.sams.exception.BasicException;
import org.nju.sesta.sams.parameter.PersonalInfo.AuthUpProcessParameter;
import org.nju.sesta.sams.parameter.PersonalInfo.BasicInfoParameter;
import org.nju.sesta.sams.parameter.PersonalInfo.DevFormInfoParameter;
import org.nju.sesta.sams.response.activityInfo.ActivityInfoResponse;
import org.nju.sesta.sams.response.activityInfo.RoughActivityInfoResponse;
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
@RequestMapping(value = "/info")
public class PersonalInfoController {

    @Autowired
    PersonalInfoService personalInfoService;

    @Autowired
    ActivityManageService activityManageService;

    @Autowired
    JwtUtil jwtUtil;

    @Value("${jwt.header}")
    String tokenHeader;

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<?> getPersonalInfo(@PathVariable String id, HttpServletRequest request) {
        if (!id.equals(jwtUtil.getUsernameFromRequest(request)))
            throw new BasicException(HttpStatus.BAD_REQUEST, "bad request");
        return ResponseEntity.ok(new PersonalInfoResponse(personalInfoService.getPersonalInfo(id)));
    }

    @RequestMapping(value = "/",
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
    public ResponseEntity<?> processAuthUpRequest(@RequestBody AuthUpProcessParameter param) {
        personalInfoService.processAuthUpRequest(param.getId(), param.getDecision());
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/activitiesJoined/list",
            method = RequestMethod.GET)
    public ResponseEntity<?> getActivitiesJoined(HttpServletRequest request) {
        String id = jwtUtil.getUsernameFromRequest(request);
        RoughActivityInfoResponse[] activities = personalInfoService.getActivityJoined(id);
        return ResponseEntity.ok(activities);
    }

    @RequestMapping(value = "/activitiesReleased/list",
            method = RequestMethod.GET)
    public ResponseEntity<?> getActivitiesReleased(HttpServletRequest request) {
        String id = jwtUtil.getUsernameFromRequest(request);
        RoughActivityInfoResponse[] activities = personalInfoService.getActivityReleased(id);
        return ResponseEntity.ok(activities);
    }

    @RequestMapping(value = "/activity/{activityId}",
            method = RequestMethod.GET)
    public ResponseEntity<?> getActivityDetail(@PathVariable String activityId) {
        return ResponseEntity.ok(new ActivityInfoResponse(activityManageService.getActivityDetail(Long.parseLong(activityId))));
    }

    @RequestMapping(value = "/devAxForm",
            method = RequestMethod.GET)
    public ResponseEntity<?> getDevAxForm(HttpServletRequest request) {
        String id = jwtUtil.getUsernameFromRequest(request);
        DevAxFormItem[] items = personalInfoService.getDev(id);
        return ResponseEntity.ok(items);
    }

    @RequestMapping(value = "/devAxForm",
            method = RequestMethod.PUT)
    public ResponseEntity<?> updateDevAxForm(@RequestBody DevFormInfoParameter devAxFormItems, HttpServletRequest request) {
        String id = jwtUtil.getUsernameFromRequest(request);
        personalInfoService.updateDevAxFormInfo(id, devAxFormItems);
        return ResponseEntity.ok(null);
    }


}
