package org.nju.sesta.sams.controller;

import org.nju.sesta.sams.entity.Activity;
import org.nju.sesta.sams.factory.ActivityFactory;
import org.nju.sesta.sams.parameter.activity.NewActivityParameter;
import org.nju.sesta.sams.parameter.activity.NewMatchParameter;
import org.nju.sesta.sams.response.activityInfo.ActivityInfoResponse;
import org.nju.sesta.sams.service.ActivityManageService;
import org.nju.sesta.sams.util.token.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"})
    public ResponseEntity<?> applyForNewMatch(@RequestBody NewMatchParameter parameter, HttpServletRequest request) {
//        activity.setCreatorId(getIdFromRequest(request));

        Activity activity = ActivityFactory.create(parameter);
        String studentId = getIdFromRequest(request);
        if (service.applyForNewMatch(activity, studentId))
            return ResponseEntity.ok(null);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/activity/new",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"})
    public ResponseEntity<?> applyForNewActivity(@RequestBody NewActivityParameter parameter, HttpServletRequest request) {
//        activity.setCreatorId(getIdFromRequest(request));

        Activity activity = ActivityFactory.create(parameter);
        String studentId = getIdFromRequest(request);
        if (service.applyForNewMatch(activity, studentId))
            return ResponseEntity.ok(null);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public ResponseEntity<?> getActivityInfo(@PathVariable String id) {
        return ResponseEntity.ok(new ActivityInfoResponse(service.getActivityInfo(Long.parseLong(id))));
    }

    @RequestMapping(value = "/{id}/signUp",
            method = RequestMethod.GET)
    public ResponseEntity<?> signUp(@PathVariable Long id, HttpServletRequest request) {
        String studentId = getIdFromRequest(request);
        if (service.signUpActivity(id, studentId))
            return ResponseEntity.ok(null);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    private String getIdFromRequest(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        return jwtToken.getUsernameFromToken(token);
    }
}
