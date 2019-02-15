package org.nju.sesta.sams.controller;

import org.nju.sesta.sams.parameter.activity.NewMatchParameter;
import org.nju.sesta.sams.response.ActivityInfo.ActivityInfoResponse;
import org.nju.sesta.sams.service.ActivityManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/activity")
public class ActivityManageController {
    @Autowired
    ActivityManageService service;

    @RequestMapping(value = "/match/new",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"})
    public ResponseEntity<?> applyForNewMatch(@RequestBody NewMatchParameter parameter){
        if(service.applyForNewMatch(parameter))
            return ResponseEntity.ok(null);
        else 
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/activity/new",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"})
    public ResponseEntity<?> applyForNewActivity(@RequestBody NewMatchParameter parameter){
        if(service.applyForNewActivity(parameter))
            return ResponseEntity.ok(null);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public ResponseEntity<?> getPersonalInfo(@PathVariable String id) {
        return ResponseEntity.ok(new ActivityInfoResponse(service.getActivityInfo(Long.parseLong(id))));
    }
}
